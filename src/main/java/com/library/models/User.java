package com.library.models;

import lombok.Data;
import org.hibernate.Query;
import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
@Data
@Entity
@Table (name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
