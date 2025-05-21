package com.mickaelsouza.schoolsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Grade;
import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.model.Subject;
import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.repository.GradeRepository;
import com.mickaelsouza.schoolsystem.repository.SubjectRepository;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GradeService {
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public List<Grade> findBySubjectId(Long subjectId) {
        return gradeRepository.findBySubjectId(subjectId);
    }

    public List<Grade> findByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public Grade create(Grade grade) {
        Long studentId = grade.getStudent().getId();

        User user = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        if (!Role.ROLE_ALUNO.equals(user.getRole())) {
            throw new RuntimeException("Somente professores podem ser associados a matérias");
        }

        grade.setStudent(user);
        return gradeRepository.save(grade);
    }
}
