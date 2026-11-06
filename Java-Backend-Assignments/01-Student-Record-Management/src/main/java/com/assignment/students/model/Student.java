package com.assignment.students.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max = 100)
    private String name;
    @NotBlank @Email @Column(nullable = false, unique = true)
    private String email;
    @Size(max = 20)
    private String phone;
    @Size(max = 150)
    private String department;

    public Student() {}
    public Student(String name, String email, String phone, String department) {
        this.name=name; this.email=email; this.phone=phone; this.department=department;
    }
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String name){this.name=name;}
    public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
    public String getPhone(){return phone;} public void setPhone(String phone){this.phone=phone;}
    public String getDepartment(){return department;} public void setDepartment(String department){this.department=department;}
}
