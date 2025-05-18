package com.mickaelsouza.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelsouza.schoolsystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
}
