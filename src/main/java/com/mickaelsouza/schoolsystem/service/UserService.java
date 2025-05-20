package com.mickaelsouza.schoolsystem.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mickaelsouza.schoolsystem.model.Role;
import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == getRole())
                .collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .filter(user -> user.getRole() == getRole());
    }

    public User create(User user) {
        user.setRole(getRole());
        return userRepository.save(user);
    }

    public User update(Long id, User updated) {
        User user = findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String updatedName = updated.getName();
        String updatedEmail = updated.getEmail();

        if (updatedName != null) {
            user.setName(updatedName);
        }

        if (updatedEmail != null) {
            user.setEmail(updatedEmail);
        }

        return userRepository.save(user);
    }

    public User updatePassword(Long id, User updated) {
        User user = findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String newPassword = passwordEncoder.encode(updated.getPassword());
        user.setPassword(newPassword);

        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    protected abstract Role getRole();
}
