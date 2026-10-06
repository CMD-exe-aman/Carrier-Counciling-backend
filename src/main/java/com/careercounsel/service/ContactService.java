package com.careercounsel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.careercounsel.model.Contact;
import com.careercounsel.repository.ContactRepository;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repo;

    public Contact save(Contact contact) {
        return repo.save(contact);
    }
}