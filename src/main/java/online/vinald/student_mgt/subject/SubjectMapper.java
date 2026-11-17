package online.vinald.student_mgt.subject;

public class SubjectMapper {
	public static SubjectResponseDto toDto(Subject subject) {
		return new SubjectResponseDto(
				subject.getId(),
				subject.getCode(),
				subject.getName(),
				subject.getDescription(),
				subject.getCredits(),
				subject.getCreatedAt(),
				subject.getUpdatedAt()
		);
	}

	public static Subject toEntity(SubjectRequestDto request) {
		Subject subject = new Subject();
		subject.setCode(request.code());
		subject.setName(request.name());
		subject.setDescription(request.description());
		subject.setCredits(request.credits());
		return subject;
	}
}
