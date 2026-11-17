package online.vinald.student_mgt.student;

public class StudentMapper {

	public static Student toEntity(StudentRequestDto request) {
		return new Student(
				request.firstName(),
				request.lastName(),
				request.email(),
				request.dateOfBirth(),
				request.course()
		);
	}

	public static void updateEntity(Student student, StudentRequestDto request) {
		student.setFirstName(request.firstName());
		student.setLastName(request.lastName());
		student.setEmail(request.email());
		student.setDateOfBirth(request.dateOfBirth());
		student.setCourse(request.course());
	}
}
