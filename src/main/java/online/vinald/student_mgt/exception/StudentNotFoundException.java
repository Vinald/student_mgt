package online.vinald.student_mgt.exception;

public class StudentNotFoundException extends RuntimeException {
	public StudentNotFoundException(Integer id) {
        super("Student with id " + id + " not found");
    }
}
