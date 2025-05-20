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

import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.service.SubjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> list() {
        return ResponseEntity.ok(subjectService.findAll());
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        return ResponseEntity.ok(subjectService.create(subject));
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
    // return subjectService.findById(id)
    // .map(ResponseEntity::ok)
    // .orElse(ResponseEntity.notFound().build());
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<?> updateSubject(@PathVariable Long id, @RequestBody
    // Subject updated) {
    // return ResponseEntity.ok(subjectService.update(id, updated));
    // }

    // @PutMapping("/password/{id}")
    // public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody
    // Subject updated) {
    // return ResponseEntity.ok(subjectService.updatePassword(id, updated));
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
    // subjectService.delete(id);
    // return ResponseEntity.ok().build();
    // }
}
