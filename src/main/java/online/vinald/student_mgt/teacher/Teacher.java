package online.vinald.student_mgt.teacher;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private  String email;
	private String phone;
	private String specialization;
	@Column(nullable = false, updatable = false)
	private LocalDate createdAt = LocalDate.now();
	private LocalDate updatedAt = LocalDate.now();
	private UUID uuid = UUID.randomUUID();

	private String getFullName(){
		return firstName + " " + lastName;
	}
}
