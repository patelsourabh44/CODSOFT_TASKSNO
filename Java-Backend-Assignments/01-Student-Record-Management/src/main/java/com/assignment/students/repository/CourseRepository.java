package com.assignment.students.repository;
import com.assignment.students.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course,Long> {}
