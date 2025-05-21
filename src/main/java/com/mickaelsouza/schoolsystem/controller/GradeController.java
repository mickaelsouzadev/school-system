package com.mickaelsouza.schoolsystem.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mickaelsouza.schoolsystem.model.Grade;
import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.service.GradeService;
import com.mickaelsouza.schoolsystem.service.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/grades")
@RequiredArgsConstructor
public class GradeController {
    private final GradeService gradeService;

    @PostMapping
    public ResponseEntity<Grade> createSubject(@RequestBody Grade grade) {
        return ResponseEntity.ok(gradeService.create(grade));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<List<Grade>> getGradeBySubjectId(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.findBySubjectId(id));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<List<Grade>> getGradeByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(gradeService.findByStudentId(id));
    }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody
    // Grade updated) {
    // return ResponseEntity.ok(gradeService.update(id, updated));
    // }
}
