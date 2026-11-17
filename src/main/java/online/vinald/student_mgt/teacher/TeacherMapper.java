package online.vinald.student_mgt.teacher;

public class TeacherMapper {

	public static TeacherResponseDto toResponseDto(Teacher teacher) {
		return new TeacherResponseDto(
				teacher.getId(),
				teacher.getFirstName(),
				teacher.getLastName(),
				teacher.getEmail(),
				teacher.getPhone(),
				teacher.getSpecialization(),
				teacher.getCreatedAt()
		);
	}

	public static Teacher toTeacher (TeacherRequestDto request) {
		if (request == null) {
			throw new NullPointerException("TeacherRequestDto cannot be null");
		}
		Teacher teacher = new Teacher();
		teacher.setFirstName(request.firstName());
		teacher.setLastName(request.lastName());
		teacher.setEmail(request.email());
		teacher.setPhone(request.phone());
		teacher.setSpecialization(request.specialization());
		return teacher;
	}
}
