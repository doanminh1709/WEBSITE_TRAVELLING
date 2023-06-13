package com.example.travel.controllers;

import com.example.travel.dtos.ContactDTO;
import com.example.travel.services.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin("http://localhost:3000")
public class ContactController {
    @Autowired
    private IContactService iContactService;

    @GetMapping
    public ResponseEntity<?> getAllContact() {
        return ResponseEntity.status(200).body(iContactService.getAllContact());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable("id") Long id) {
        return ResponseEntity.status(200).body(iContactService.getContactById(id));
    }

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody @Valid ContactDTO contactDTO) {
        return ResponseEntity.status(201).body(iContactService.createContact(contactDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editContactById(@PathVariable("id") Long id, @RequestBody @Valid ContactDTO contactDTO) {
        return ResponseEntity.status(201).body(iContactService.editContactById(id, contactDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContactById(@PathVariable("id") Long id) {
        iContactService.deleteContactById(id);
        return ResponseEntity.status(200).body("Delete Success");
    }
}
