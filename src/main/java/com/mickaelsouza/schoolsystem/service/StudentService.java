package com.mickaelsouza.schoolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

@Service
public class StudentService extends UserService {
    public StudentService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository, passwordEncoder);
    }

    @Override
    protected Role getRole() {
        return Role.ROLE_ALUNO;
    }
}