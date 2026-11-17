package online.vinald.student_mgt.student;

import online.vinald.student_mgt.exception.DuplicateEmailException;
import online.vinald.student_mgt.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudentService {
	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public List<Student> getAll() {
		return repository.findAll();
	}

	public Student getById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student", id));
	}

	public List<Student> searchByName(String name) {
		return repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
	}

	public Student create(StudentRequestDto request) {
		if (repository.existsByEmail(request.email())){
			throw new DuplicateEmailException(request.email());
		}
		Student student = StudentMapper.toEntity(request);
		return repository.save(student);
	}

	public Student update(Long id, StudentRequestDto request) {
		Student student = getById(id);
		if (!student.getEmail().equals(request.email()) && repository.existsByEmail(request.email())) {
			throw new DuplicateEmailException(request.email());
		}
		StudentMapper.updateEntity(student, request);
		student.setUpdatedAt(LocalDate.now());
		return repository.save(student);
	}

	public void delete(Long id){
		Student student = getById(id);
		repository.delete(student);
	}
}
