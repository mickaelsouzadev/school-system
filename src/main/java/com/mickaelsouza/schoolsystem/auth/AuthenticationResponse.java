package com.mickaelsouza.schoolsystem.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AuthenticationResponse {
    private String token;
    private String name;
    private String role;

    public AuthenticationResponse(String token, String name, String role) {
        this.token = token;
        this.name = name;
        this.role = role;
    }
}
