package com.mickaelsouza.schoolsystem.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends User{
    @ManyToMany(mappedBy = "students")
    private List<Subject> subjects;
}
