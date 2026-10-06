package com.careercounsel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.careercounsel.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}