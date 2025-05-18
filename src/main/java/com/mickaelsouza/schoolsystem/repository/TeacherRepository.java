package com.mickaelsouza.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelsouza.schoolsystem.model.Teacher;

public interface TeacherRepository extends JpaRepository <Teacher, Long> {
Teacher findByEmail(String email);
}
