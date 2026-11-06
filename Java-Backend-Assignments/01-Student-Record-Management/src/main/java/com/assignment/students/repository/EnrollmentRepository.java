package com.assignment.students.repository;
import com.assignment.students.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    boolean existsByStudentIdAndCourseId(Long studentId,Long courseId);
}
