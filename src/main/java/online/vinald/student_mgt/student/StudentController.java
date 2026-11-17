package online.vinald.student_mgt.student;

import jakarta.validation.Valid;
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
	public Student getStudentById(@PathVariable Long id) {
		return service.getById(id);
	}

	@GetMapping("/search")
	public List<Student> searchStudents(@RequestParam String name){
		return service.searchByName(name);
	}

	@PostMapping
	public Student createStudent(@Valid @RequestBody StudentRequestDto request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDto request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
