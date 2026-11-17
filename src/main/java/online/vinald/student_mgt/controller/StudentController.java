package online.vinald.student_mgt.controller;

import jakarta.validation.Valid;
import online.vinald.student_mgt.dto.StudentRequest;
import online.vinald.student_mgt.model.Student;
import online.vinald.student_mgt.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Student getStudentById(Integer id) {
		return service.getById(id);
	}

	@GetMapping("/search")
	public List<Student> searchStudents(@RequestParam String name){
		return service.searchByName(name);
	}

	@PostMapping
	public Student createStudent(@Valid @RequestBody StudentRequest request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Integer id, @Valid @RequestBody StudentRequest request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
