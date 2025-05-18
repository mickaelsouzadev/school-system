package com.mickaelsouza.schoolsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Teacher;
import com.mickaelsouza.schoolsystem.repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public Teacher create(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher update(Long id, Teacher updated) {
        return teacherRepository.findById(id)
                .map(t -> {
                    t.setName(updated.getName());
                    t.setEmail(updated.getEmail());
                    return teacherRepository.save(t);
                }).orElseThrow(() -> new RuntimeException("Professor não encontrado"));
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}
