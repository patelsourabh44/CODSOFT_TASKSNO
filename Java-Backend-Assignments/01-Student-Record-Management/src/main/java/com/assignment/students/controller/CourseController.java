package com.assignment.students.controller;
import com.assignment.students.model.Course;
import com.assignment.students.repository.CourseRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/courses")
public class CourseController {
    private final CourseRepository repo;
    public CourseController(CourseRepository repo){this.repo=repo;}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Course create(@Valid @RequestBody Course c){return repo.save(c);}
    @GetMapping public Page<Course> list(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){
        if(page<0||size<1||size>100)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid page or size");
        return repo.findAll(PageRequest.of(page,size,Sort.by("title").ascending()));
    }
    @GetMapping("/{id}") public Course get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found"));}
    @PutMapping("/{id}") public Course update(@PathVariable Long id,@Valid @RequestBody Course b){Course c=get(id);c.setTitle(b.getTitle());c.setCode(b.getCode());c.setDescription(b.getDescription());c.setCredits(b.getCredits());return repo.save(c);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Course not found");repo.deleteById(id);}
}
