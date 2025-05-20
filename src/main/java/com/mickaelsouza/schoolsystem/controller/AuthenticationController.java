package com.mickaelsouza.schoolsystem.controller;

import com.mickaelsouza.schoolsystem.auth.AuthenticationRequest;
import com.mickaelsouza.schoolsystem.auth.AuthenticationResponse;
import com.mickaelsouza.schoolsystem.service.AuthenticationService;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
}
