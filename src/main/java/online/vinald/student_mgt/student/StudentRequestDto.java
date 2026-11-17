package online.vinald.student_mgt.student;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record StudentRequestDto(
		@NotBlank(message = "First name is required") String firstName,
		@NotBlank(message = "Last name is required") String lastName,
		@NotBlank(message = "Email is required")
		@Email(message = "Email should be valid") String email,
		@Past(message = "Date of birth must be in the past") LocalDate dateOfBirth,
		@NotBlank(message = "Course is required") String course
) {
}
