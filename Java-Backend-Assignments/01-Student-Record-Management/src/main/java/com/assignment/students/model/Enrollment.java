package com.assignment.students.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="enrollments", uniqueConstraints=@UniqueConstraint(columnNames={"student_id","course_id"}))
public class Enrollment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull @ManyToOne(optional=false) @JoinColumn(name="student_id")
    private Student student;
    @NotNull @ManyToOne(optional=false) @JoinColumn(name="course_id")
    private Course course;
    private LocalDate enrollmentDate;
    @Column(length=20)
    private String status;

    @PrePersist
    public void onCreate(){ if(enrollmentDate==null) enrollmentDate=LocalDate.now(); if(status==null) status="ACTIVE"; }
    public Enrollment() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public Student getStudent(){return student;} public void setStudent(Student student){this.student=student;}
    public Course getCourse(){return course;} public void setCourse(Course course){this.course=course;}
    public LocalDate getEnrollmentDate(){return enrollmentDate;} public void setEnrollmentDate(LocalDate enrollmentDate){this.enrollmentDate=enrollmentDate;}
    public String getStatus(){return status;} public void setStatus(String status){this.status=status;}
}
