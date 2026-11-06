package com.assignment.todo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity @Table(name="tasks")
public class Task {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Size(max=150) @Column(nullable=false) private String title;
 @Size(max=1000) private String description;
 @NotNull @Enumerated(EnumType.STRING) @Column(nullable=false) private TaskStatus status=TaskStatus.PENDING;
 @Enumerated(EnumType.STRING) private Priority priority=Priority.MEDIUM;
 private LocalDate dueDate;
 private LocalDateTime createdAt;
 @PrePersist public void created(){createdAt=LocalDateTime.now();if(status==null)status=TaskStatus.PENDING;if(priority==null)priority=Priority.MEDIUM;}
 public Task(){}
 public Long getId(){return id;} public void setId(Long v){id=v;}
 public String getTitle(){return title;} public void setTitle(String v){title=v;}
 public String getDescription(){return description;} public void setDescription(String v){description=v;}
 public TaskStatus getStatus(){return status;} public void setStatus(TaskStatus v){status=v;}
 public Priority getPriority(){return priority;} public void setPriority(Priority v){priority=v;}
 public LocalDate getDueDate(){return dueDate;} public void setDueDate(LocalDate v){dueDate=v;}
 public LocalDateTime getCreatedAt(){return createdAt;} public void setCreatedAt(LocalDateTime v){createdAt=v;}
}
