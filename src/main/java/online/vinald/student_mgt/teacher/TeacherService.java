package online.vinald.student_mgt.teacher;

import lombok.RequiredArgsConstructor;
import online.vinald.student_mgt.exception.ConflictException;
import online.vinald.student_mgt.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public List<TeacherResponseDto> getAll() {
        return teacherRepository.findAll().stream().map(TeacherMapper::toDto).toList();
    }

    public TeacherResponseDto getById(Long id) {
        return TeacherMapper.toDto(findEntity(id));
    }

    public TeacherResponseDto create(TeacherRequestDto request) {
        if (teacherRepository.existsByEmail(request.email())) {
            throw new ConflictException("A teacher with email " + request.email() + " already exists");
        }
        Teacher teacher = new Teacher();
        TeacherMapper.updateEntity(teacher, request);
        return TeacherMapper.toDto(teacherRepository.save(teacher));
    }

    public TeacherResponseDto update(Long id, TeacherRequestDto request) {
        Teacher teacher = findEntity(id);
        if (!teacher.getEmail().equals(request.email())
                && teacherRepository.existsByEmail(request.email())) {
            throw new ConflictException("A teacher with email " + request.email() + " already exists");
        }
        TeacherMapper.updateEntity(teacher, request);
        return TeacherMapper.toDto(teacherRepository.save(teacher));
    }

    public void delete(Long id) {
        teacherRepository.delete(findEntity(id));
        teacherRepository.flush(); // surfaces foreign-key errors here so the handler can catch them
    }

    // Used by other services that need the actual entity
    public Teacher findEntity(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher", id));
    }
}
