package online.vinald.student_mgt.teacher;

public class TeacherMapper {

	public static TeacherResponseDto toDto(Teacher teacher) {
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

	public static Teacher toEntity(TeacherRequestDto request) {
		Teacher teacher = new Teacher();
		updateEntity(teacher, request);
		return teacher;
	}

	public static void updateEntity(Teacher teacher, TeacherRequestDto request) {
		teacher.setFirstName(request.firstName());
		teacher.setLastName(request.lastName());
		teacher.setEmail(request.email());
		teacher.setPhone(request.phone());
		teacher.setSpecialization(request.specialization());
	}
}
