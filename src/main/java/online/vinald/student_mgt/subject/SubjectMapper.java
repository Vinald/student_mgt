package online.vinald.student_mgt.subject;

import jakarta.validation.constraints.Null;

public class SubjectMapper {
	public static SubjectResponseDto toSubjectResponseDto(Subject subject) {
		if(subject == null) {
			throw new NullPointerException("Subject cannot be null");
		}
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

	public static Subject toSubject(SubjectResponseDto subjectResponseDto) {
		if(subjectResponseDto == null) {
			throw new NullPointerException("SubjectResponseDto cannot be null");
		}
		Subject subject = new Subject();
		subject.setCode(subjectResponseDto.code());
		subject.setName(subjectResponseDto.name());
		subject.setDescription(subjectResponseDto.description());
		subject.setCredits(subjectResponseDto.credits());
		subject.setUpdatedAt(subjectResponseDto.updatedAt());
		return subject;
	}
}
