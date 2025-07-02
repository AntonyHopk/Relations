package org.example.relations.services;

import org.example.relations.DTO.JwtResponse;
import org.example.relations.DTO.LoginRequest;
import org.example.relations.DTO.RefreshRequest;
import org.example.relations.DTO.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);

    JwtResponse login(LoginRequest request);

    JwtResponse refreshToken(RefreshRequest request);
}
