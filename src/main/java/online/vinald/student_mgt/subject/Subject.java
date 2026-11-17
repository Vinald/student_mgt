package online.vinald.student_mgt.subject;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import online.vinald.student_mgt.teacher.Teacher;

import java.time.LocalDate;

@Entity
@Table(name = "subjects")
@Data
@NoArgsConstructor
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private String name;
	private  String description;
	private Integer credits;
	private LocalDate createdAt = LocalDate.now();
	private LocalDate updatedAt = LocalDate.now();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	public Subject(String code, String name, String description) {
		this.code = code;
		this.name = name;
	}
}
