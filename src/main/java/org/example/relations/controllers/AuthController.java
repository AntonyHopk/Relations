package org.example.relations.controllers;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.RegisterRequest;
import org.example.relations.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("Register was successfully");
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Authentication authentication) {
        return ResponseEntity.ok("Login was successful " + authentication.getName());
    }
}
