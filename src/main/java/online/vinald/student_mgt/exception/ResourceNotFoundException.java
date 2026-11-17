package online.vinald.student_mgt.exception;

public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String resourceName, Integer id) {
		super(String.format("%s with id %d not found", resourceName, id));
	}
}
