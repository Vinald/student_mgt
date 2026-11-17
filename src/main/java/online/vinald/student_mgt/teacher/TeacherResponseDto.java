package online.vinald.student_mgt.teacher;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.coyote.Response;

import java.time.LocalDate;

public record TeacherResponseDto(
		Long id,
		String firstName,
		String lastName,
		String email,
		String phone,
		String specialization,
		LocalDate createdAt
) {}
