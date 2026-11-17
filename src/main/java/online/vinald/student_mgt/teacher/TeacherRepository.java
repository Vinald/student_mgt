package online.vinald.student_mgt.teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	boolean existsByEmail(String email);
}
