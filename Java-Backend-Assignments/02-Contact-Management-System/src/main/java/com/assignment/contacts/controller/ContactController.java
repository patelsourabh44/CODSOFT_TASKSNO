package com.assignment.contacts.controller;
import com.assignment.contacts.model.Contact;
import com.assignment.contacts.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController @RequestMapping("/api/contacts")
public class ContactController {
 private final ContactRepository repo;
 public ContactController(ContactRepository repo){this.repo=repo;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Contact create(@Valid @RequestBody Contact c){return repo.save(c);}
 @GetMapping public Page<Contact> list(@RequestParam(defaultValue="") String search,@RequestParam(defaultValue="0") int page,
   @RequestParam(defaultValue="10") int size,@RequestParam(defaultValue="name") String sortBy,@RequestParam(defaultValue="asc") String direction){
   if(page<0||size<1||size>100)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid page or size (1-100)");
   Sort sort=direction.equalsIgnoreCase("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
   Pageable p=PageRequest.of(page,size,sort);
   return search.isBlank()?repo.findAll(p):repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(search,search,search,p);
 }
 @GetMapping("/{id}") public Contact get(@PathVariable Long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found"));}
 @PutMapping("/{id}") public Contact update(@PathVariable Long id,@Valid @RequestBody Contact b){Contact c=get(id);c.setName(b.getName());c.setEmail(b.getEmail());c.setPhone(b.getPhone());c.setAddress(b.getAddress());c.setCompany(b.getCompany());return repo.save(c);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){if(!repo.existsById(id))throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Contact not found");repo.deleteById(id);}
}
