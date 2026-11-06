package com.assignment.students.controller;

import com.assignment.students.model.Student;
import com.assignment.students.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentRepository repo;
    public StudentController(StudentRepository repo){this.repo=repo;}

    @PostMapping @ResponseStatus(HttpStatus.CREATED)
    public Student create(@Valid @RequestBody Student student){return repo.save(student);}

    @GetMapping
    public Page<Student> list(@RequestParam(defaultValue="") String search,
        @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size,
        @RequestParam(defaultValue="id") String sortBy,@RequestParam(defaultValue="asc") String direction){
        if(page<0 || size<1 || size>100) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid page or size (1-100).");
        Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        Pageable pageable=PageRequest.of(page,size,sort);
        return search.isBlank()?repo.findAll(pageable):
            repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search,search,pageable);
    }
    @GetMapping("/{id}") public Student get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found"));}
    @PutMapping("/{id}") public Student update(@PathVariable Long id,@Valid @RequestBody Student body){
        Student s=get(id); s.setName(body.getName());s.setEmail(body.getEmail());s.setPhone(body.getPhone());s.setDepartment(body.getDepartment());return repo.save(s);
    }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Student not found");repo.deleteById(id);}
}
