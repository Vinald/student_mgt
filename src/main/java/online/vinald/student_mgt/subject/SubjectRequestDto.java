package online.vinald.student_mgt.subject;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record SubjectRequestDto(
		@NotBlank(message = "Code is required") String code,
		@NotBlank(message = "Name is required") String name,
		String description,
		@Positive(message = "Credits must be a positive number") Integer credits,
		LocalDate updatedAt,
		Long teacherId
) {
}
