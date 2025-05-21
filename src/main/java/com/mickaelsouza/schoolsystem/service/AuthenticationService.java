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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.BadCredentialsException;


@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()));

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());

            return new AuthenticationResponse(token, user.getName(), user.getRole().name());

        } catch (AuthenticationException e) {
           throw new BadCredentialsException("Usuário ou senha incorretos");
        }
    }
}
