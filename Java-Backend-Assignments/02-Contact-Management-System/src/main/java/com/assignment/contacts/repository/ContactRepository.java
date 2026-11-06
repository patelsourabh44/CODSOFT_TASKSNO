package com.assignment.contacts.repository;
import com.assignment.contacts.model.Contact;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ContactRepository extends JpaRepository<Contact,Long> {
 Page<Contact> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCase(String name,String email,String phone,Pageable pageable);
}
