package online.vinald.student_mgt.subject;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import online.vinald.student_mgt.teacher.Teacher;
import online.vinald.student_mgt.teacher.TeacherRepository;
import online.vinald.student_mgt.teacher.TeacherService;
import org.springframework.stereotype.Service;

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
				.map(SubjectMapper::toSubjectResponseDto)
				.toList();
	}
}
