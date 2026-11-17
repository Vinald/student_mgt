package online.vinald.student_mgt.subject;

public class SubjectMapper {
	public static SubjectResponseDto toDto(Subject subject) {
		return new SubjectResponseDto(
				subject.getId(),
				subject.getCode(),
				subject.getName(),
				subject.getDescription(),
				subject.getCredits(),
				subject.getTeacher() != null ? subject.getTeacher().getId() : null,
				subject.getCreatedAt(),
				subject.getUpdatedAt()
		);
	}

	public static Subject toEntity(SubjectRequestDto request) {
		Subject subject = new Subject();
		updateEntity(subject, request);
		return subject;
	}

	public static void updateEntity(Subject subject, SubjectRequestDto request) {
		subject.setCode(request.code());
		subject.setName(request.name());
		subject.setDescription(request.description());
		subject.setCredits(request.credits());
	}
}
