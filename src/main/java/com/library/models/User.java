package com.library.models;

import org.hibernate.annotations.NaturalId;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_subscriptions",
            joinColumns = { @JoinColumn(name = "subscriber_id")},
            inverseJoinColumns = { @JoinColumn(name = "book_id")}
    )
    private Set<Book> books = new HashSet<>();

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> bookSet) {
        this.books = bookSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(Role role) {
        this.role = role;


    }

    public void subscribe(Book book) {
        getBooks().add(book);
    }
}
