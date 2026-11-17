package online.vinald.student_mgt.teacher;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	private final TeacherService service;

	public  TeacherController(TeacherService service) {
		this.service = service;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<TeacherResponseDto> getAllTeachers() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TeacherResponseDto getTeacherById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TeacherResponseDto createTeacher(@Valid @RequestBody TeacherRequestDto request) {
		return service.create(request);
	}

	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TeacherResponseDto updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherRequestDto request) {
		return service.update(id, request);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public  void deleteTeacher(@PathVariable Long id) {
		service.delete(id);
	}
}
