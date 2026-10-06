package com.careercounsel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.careercounsel.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}