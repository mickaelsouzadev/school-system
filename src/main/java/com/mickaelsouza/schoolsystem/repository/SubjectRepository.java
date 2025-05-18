package com.mickaelsouza.schoolsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelsouza.schoolsystem.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
