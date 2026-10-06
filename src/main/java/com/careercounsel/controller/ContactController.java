package com.careercounsel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.careercounsel.model.Contact;
import com.careercounsel.service.ContactService;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    public Contact saveContact(@RequestBody Contact contact) {
        return service.save(contact);
    }
}