package online.vinald.student_mgt.service;

import online.vinald.student_mgt.dto.StudentRequest;
import online.vinald.student_mgt.exception.DuplicateEmailException;
import online.vinald.student_mgt.model.Student;
import online.vinald.student_mgt.repository.StudentRepository;
import org.springframework.stereotype.Service;
import online.vinald.student_mgt.exception.StudentNotFoundException;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService {
	private final StudentRepository repository;

	public StudentService(StudentRepository repository) {
		this.repository = repository;
	}

	public List<Student> getAll() {
		return repository.findAll();
	}

	public Student getById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new StudentNotFoundException(id));
	}

	public List<Student> searchByName(String name) {
		return repository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
	}

	public Student create(StudentRequest request) {
		if (repository.existsByEmail(request.email())){
			throw new DuplicateEmailException(request.email());
		}
		Student student = new Student(
				request.firstName(),
				request.lastName(),
				request.email(),
				request.dateOfBirth(),
				request.course());
		return repository.save(student);
	}

	public Student update(Integer id, StudentRequest request) {
		Student student = getById(id);
		if (!student.getEmail().equals(request.email()) && repository.existsByEmail(request.email())) {
			throw new DuplicateEmailException(request.email());
		}
		student.setFirstName(request.firstName());
		student.setLastName(request.lastName());
		student.setEmail(request.email());
		student.setDateOfBirth(request.dateOfBirth());
		student.setCourse(request.course());
		student.setUpdatedAt(LocalDate.now());
		return repository.save(student);
	}

	public void delete(Integer id){
		Student student = getById(id);
		repository.delete(student);
	}
}
