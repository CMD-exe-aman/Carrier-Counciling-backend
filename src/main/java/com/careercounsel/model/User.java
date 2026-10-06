package com.careercounsel.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")   // 🔥 FORCE correct table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;
}