package com.assignment.contacts.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
@Entity @Table(name="contacts", uniqueConstraints={@UniqueConstraint(columnNames="email"),@UniqueConstraint(columnNames="phone")})
public class Contact {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @NotBlank @Size(max=100) @Column(nullable=false) private String name;
    @NotBlank @Email @Column(nullable=false,unique=true) private String email;
    @NotBlank @Size(max=20) @Column(nullable=false,unique=true) private String phone;
    @Size(max=255) private String address;
    @Size(max=120) private String company;
    public Contact(){}
    public Long getId(){return id;} public void setId(Long id){this.id=id;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getEmail(){return email;} public void setEmail(String v){email=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getCompany(){return company;} public void setCompany(String v){company=v;}
}
