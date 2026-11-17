package online.vinald.student_mgt.subject;

import java.time.LocalDate;

public record SubjectResponseDto(
		Long id,
		String code,
		String name,
		String description,
		Integer credits,
		Long teacherId,
		LocalDate createdAt,
		LocalDate updatedAt
) {
}
