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
        return teacherRepository.findAll().stream().map(TeacherMapper::toResponseDto).toList();
    }

    public TeacherResponseDto getById(Long id) {
        return TeacherMapper.toResponseDto(findEntity(id));
    }

    public TeacherResponseDto create(TeacherRequestDto request) {
        if (teacherRepository.existsByEmail(request.email())) {
            throw new ConflictException("A teacher with email " + request.email() + " already exists");
        }
        Teacher teacher = TeacherMapper.toTeacher(request);
		teacherRepository.save(teacher);
        return TeacherMapper.toResponseDto(teacher);
    }

    public TeacherResponseDto update(Long id, TeacherRequestDto request) {
        Teacher teacher = findEntity(id);
        if (!teacher.getEmail().equals(request.email())
                && teacherRepository.existsByEmail(request.email())) {
            throw new ConflictException("A teacher with email " + request.email() + " already exists");
        }
        TeacherMapper.toTeacher(request);
		teacherRepository.save(teacher);
        return TeacherMapper.toResponseDto(teacher);
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
