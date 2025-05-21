package com.mickaelsouza.schoolsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.repository.SubjectRepository;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    public Subject create(Subject subject) {
        Long teacherId = subject.getTeacher().getId();

        User user = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (!Role.ROLE_PROFESSOR.equals(user.getRole())) {
            throw new RuntimeException("Somente professores podem ser associados a matérias");
        }

        List<User> students = getStudents(subject.getStudents());

        subject.setTeacher(user);
        subject.setStudents(students);
        return subjectRepository.save(subject);
    }

    private List<User> getStudents(List<User> users) {
        if (users.size() == 0) {
            return users;
        }

        List<Long> ids = new ArrayList<>();

        for (User user : users) {
            ids.add(user.getId());
        }

        List<User> students = userRepository.findAllById(ids).stream()
                .filter(user -> user.getRole() == Role.ROLE_ALUNO)
                .collect(Collectors.toList());

        if (students.size() != ids.size()) {
            throw new IllegalArgumentException("Um ou mais IDs não correspondem a estudantes existentes.");
        }
        return students;
    }
}
