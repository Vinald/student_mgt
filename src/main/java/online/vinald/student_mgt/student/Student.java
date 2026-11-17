package online.vinald.student_mgt.student;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	private LocalDate dateOfBirth;
	private String course;
	@Column(updatable = false, nullable = false)
	private LocalDate createdAt = LocalDate.now();
	private LocalDate updatedAt = LocalDate.now();
	private UUID uuid = UUID.randomUUID();

	public  Student(String firstName, String lastName, String email, LocalDate dateOfBirth, String course) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.course = course;
		this.createdAt = LocalDate.now();
		this.uuid = UUID.randomUUID();
		this.updatedAt = LocalDate.now();
	}
}
