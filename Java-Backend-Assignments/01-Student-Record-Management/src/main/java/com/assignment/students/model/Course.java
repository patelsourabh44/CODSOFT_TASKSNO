package com.assignment.students.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="courses")
public class Course {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotBlank @Size(max=120)
    @Column(nullable=false)
    private String title;
    @NotBlank @Size(max=20)
    @Column(nullable=false, unique=true)
    private String code;
    @Size(max=500)
    private String description;
    @PositiveOrZero
    private Integer credits;

    public Course() {}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getTitle(){return title;} public void setTitle(String title){this.title=title;}
    public String getCode(){return code;} public void setCode(String code){this.code=code;}
    public String getDescription(){return description;} public void setDescription(String description){this.description=description;}
    public Integer getCredits(){return credits;} public void setCredits(Integer credits){this.credits=credits;}
}
