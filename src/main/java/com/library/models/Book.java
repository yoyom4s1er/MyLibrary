package com.library.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, time_to_export, time_to_import;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime_to_export() {
        return time_to_export;
    }

    public void setTime_to_export(String time_to_export) {
        this.time_to_export = time_to_export;
    }

    public String getTime_to_import() {
        return time_to_import;
    }

    public void setTime_to_import(String time_to_import) {
        this.time_to_import = time_to_import;
    }

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(String name, String time_to_export) {
        this.name = name;
        this.time_to_export = time_to_export;
    }
}
