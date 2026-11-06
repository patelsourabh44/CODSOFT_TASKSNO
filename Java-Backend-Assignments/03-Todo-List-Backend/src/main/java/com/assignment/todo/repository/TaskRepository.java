package com.assignment.todo.repository;
import com.assignment.todo.model.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task,Long> {
 Page<Task> findByStatus(TaskStatus status,Pageable pageable);
 Page<Task> findByTitleContainingIgnoreCase(String title,Pageable pageable);
 Page<Task> findByStatusAndTitleContainingIgnoreCase(TaskStatus status,String title,Pageable pageable);
}
