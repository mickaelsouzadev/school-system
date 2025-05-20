package com.mickaelsouza.schoolsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

@Service
public class TeacherService extends UserService {
    public TeacherService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(userRepository, passwordEncoder);
    }

    @Override
    protected Role getRole() {
        return Role.ROLE_PROFESSOR;
    }
}
