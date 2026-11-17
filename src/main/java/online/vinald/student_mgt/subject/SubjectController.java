package online.vinald.student_mgt.subject;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public List<SubjectResponseDto> getAll() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectResponseDto getById(@PathVariable Long id) {
        return subjectService.getById(id);
    }

    @PostMapping
    public ResponseEntity<SubjectResponseDto> create(@Valid @RequestBody SubjectRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectService.create(request));
    }

    @PutMapping("/{id}")
    public SubjectResponseDto update(@PathVariable Long id, @Valid @RequestBody SubjectRequestDto request) {
        return subjectService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
