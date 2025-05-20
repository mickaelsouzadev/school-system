package com.mickaelsouza.schoolsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.repository.SubjectRepository;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject create(Subject subject) {
        long teacherId = subject.getTeacher().getId();

        User user = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (user.getRole() == Role.ROLE_PROFESSOR) {
            return subjectRepository.save(subject);
        }

        throw new RuntimeException("Somente professor pode ser cadastrado a uma matéria");
    }
}
