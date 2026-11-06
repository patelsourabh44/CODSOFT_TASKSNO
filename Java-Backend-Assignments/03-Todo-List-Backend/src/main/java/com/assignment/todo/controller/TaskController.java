package com.assignment.todo.controller;
import com.assignment.todo.model.*;
import com.assignment.todo.repository.TaskRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/tasks")
public class TaskController {
 private final TaskRepository repo;
 public TaskController(TaskRepository repo){this.repo=repo;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Task create(@Valid @RequestBody Task t){return repo.save(t);}
 @GetMapping public Page<Task> list(@RequestParam(required=false) TaskStatus status,@RequestParam(defaultValue="") String search,
  @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size,
  @RequestParam(defaultValue="createdAt") String sortBy,@RequestParam(defaultValue="desc") String direction){
  if(page<0||size<1||size>100)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid page or size (1-100)");
  Sort sort=direction.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
  Pageable p=PageRequest.of(page,size,sort);
  if(status!=null&&!search.isBlank())return repo.findByStatusAndTitleContainingIgnoreCase(status,search,p);
  if(status!=null)return repo.findByStatus(status,p);
  if(!search.isBlank())return repo.findByTitleContainingIgnoreCase(search,p);
  return repo.findAll(p);
 }
 @GetMapping("/{id}") public Task get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found"));}
 @PutMapping("/{id}") public Task update(@PathVariable Long id,@Valid @RequestBody Task b){Task t=get(id);t.setTitle(b.getTitle());t.setDescription(b.getDescription());t.setStatus(b.getStatus());t.setPriority(b.getPriority());t.setDueDate(b.getDueDate());return repo.save(t);}
 @PatchMapping("/{id}/status") public Task updateStatus(@PathVariable Long id,@RequestParam TaskStatus status){Task t=get(id);t.setStatus(status);return repo.save(t);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Task not found");repo.deleteById(id);}
}
