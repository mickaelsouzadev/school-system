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

import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @PostMapping
    public ResponseEntity<User> createTeacher(@RequestBody User teacher) {
        return ResponseEntity.ok(teacherService.create(teacher));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getTeacherById(@PathVariable Long id) {
        return teacherService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody User updated) {
        return ResponseEntity.ok(teacherService.update(id, updated));
    }

    @PutMapping("/password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody User updated) {
        return ResponseEntity.ok(teacherService.updatePassword(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.ok().build();
    }
}
