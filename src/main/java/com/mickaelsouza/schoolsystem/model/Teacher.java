package com.mickaelsouza.schoolsystem.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="teachers")
public class Teacher extends User {
    @OneToMany(mappedBy = "teacher")
    private List<Subject> subjects;
}
