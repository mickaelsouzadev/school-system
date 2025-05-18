package com.mickaelsouza.schoolsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Student;
import com.mickaelsouza.schoolsystem.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Long id, Student updated) {
        return studentRepository.findById(id)
                .map(s -> {
                    s.setName(updated.getName());
                    s.setEmail(updated.getEmail());
                    return studentRepository.save(s);
                }).orElseThrow(
                        () -> new RuntimeException("Aluno não encontrado"));
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
