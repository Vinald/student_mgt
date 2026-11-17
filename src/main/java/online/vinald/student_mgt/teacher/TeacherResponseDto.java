package online.vinald.student_mgt.teacher;

import java.time.LocalDate;

public record TeacherResponseDto(
		Long id,
		String firstName,
		String lastName,
		String email,
		String phone,
		String specialization,
		LocalDate createdAt,
		LocalDate updatedAt
) {}
