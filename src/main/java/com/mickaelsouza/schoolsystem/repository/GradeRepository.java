package com.mickaelsouza.schoolsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mickaelsouza.schoolsystem.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findBySubjectId(Long subjectId);
}
