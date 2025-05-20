package com.mickaelsouza.schoolsystem.service;

import com.mickaelsouza.schoolsystem.model.User;
import com.mickaelsouza.schoolsystem.repository.UserRepository;
import com.mickaelsouza.schoolsystem.security.JwtUtil;

import lombok.AllArgsConstructor;

import com.mickaelsouza.schoolsystem.auth.AuthenticationRequest;
import com.mickaelsouza.schoolsystem.auth.AuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthenticationService {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

            return new AuthenticationResponse(token);

        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
