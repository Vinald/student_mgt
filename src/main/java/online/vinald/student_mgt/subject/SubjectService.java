package online.vinald.student_mgt.subject;

import jakarta.transaction.Transactional;
import online.vinald.student_mgt.exception.ConflictException;
import online.vinald.student_mgt.exception.ResourceNotFoundException;
import online.vinald.student_mgt.teacher.TeacherService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SubjectService {
	private  final SubjectRepository subjectRepository;
	private   final TeacherService teacherService;

	public SubjectService(SubjectRepository subjectRepository, TeacherService teacherService) {
		this.subjectRepository = subjectRepository;
		this.teacherService = teacherService;
	}

	public List<SubjectResponseDto> getAllSubjects() {
		return subjectRepository.findAll()
				.stream()
				.map(SubjectMapper::toDto)
				.toList();
	}

	public SubjectResponseDto getById(Long id) {
		return SubjectMapper.toDto(findEntity(id));
	}

	public List<SubjectResponseDto> getByTeacher(Long teacherId) {
        teacherService.findEntity(teacherId);
        return subjectRepository.findByTeacherId(teacherId).stream()
                .map(SubjectMapper::toDto).toList();
    }

    public SubjectResponseDto create(SubjectRequestDto request) {
        if (subjectRepository.existsByCode(request.code())) {
            throw new ConflictException("Subject code " + request.code() + " already exists");
        }
        Subject subject = SubjectMapper.toEntity(request);
		subjectRepository.save(subject);
        return SubjectMapper.toDto(subject);
    }

    public SubjectResponseDto update(Long id, SubjectRequestDto request) {
        Subject subject = findEntity(id);
        if (!subject.getCode().equals(request.code()) && subjectRepository.existsByCode(request.code())) {
            throw new ConflictException("Subject code " + request.code() + " already exists");
        }
        SubjectMapper.updateEntity(subject, request);
        subject.setUpdatedAt(LocalDate.now());
		subjectRepository.save(subject);
        return SubjectMapper.toDto(subject);
    }

    public void delete(Long id) {
        subjectRepository.delete(findEntity(id));
        subjectRepository.flush();
    }

	public Subject findEntity(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subject", id));
    }
}
