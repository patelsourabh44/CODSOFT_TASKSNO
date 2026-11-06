package com.assignment.students.controller;
import com.assignment.students.model.*;
import com.assignment.students.repository.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import jakarta.validation.Valid;

@RestController @RequestMapping("/api/enrollments")
public class EnrollmentController {
    private final EnrollmentRepository enrollments; private final StudentRepository students; private final CourseRepository courses;
    public EnrollmentController(EnrollmentRepository e,StudentRepository s,CourseRepository c){enrollments=e;students=s;courses=c;}
    public static class EnrollmentRequest {
        @NotNull public Long studentId;
        @NotNull public Long courseId;
        public String status;
    }
    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Enrollment create(@Valid @RequestBody EnrollmentRequest r){
        Student s=students.findById(r.studentId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
        Course c=courses.findById(r.courseId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        if(enrollments.existsByStudentIdAndCourseId(r.studentId,r.courseId))throw new ResponseStatusException(HttpStatus.CONFLICT,"Student is already enrolled in this course");
        Enrollment e=new Enrollment();e.setStudent(s);e.setCourse(c);if(r.status!=null)e.setStatus(r.status);return enrollments.save(e);
    }
    @GetMapping public java.util.List<Enrollment> list(){return enrollments.findAll();}

    @PutMapping("/{id}")
    public Enrollment update(@PathVariable Long id, @Valid @RequestBody EnrollmentRequest r){
        Enrollment e=enrollments.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Enrollment not found"));
        Student s=students.findById(r.studentId)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));
        Course c=courses.findById(r.courseId)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));
        if(!e.getStudent().getId().equals(r.studentId) || !e.getCourse().getId().equals(r.courseId)){
            if(enrollments.existsByStudentIdAndCourseId(r.studentId,r.courseId))
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Student is already enrolled in this course");
        }
        e.setStudent(s); e.setCourse(c); if(r.status!=null)e.setStatus(r.status);
        return enrollments.save(e);
    }

    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){if(!enrollments.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Enrollment not found");enrollments.deleteById(id);}
}
