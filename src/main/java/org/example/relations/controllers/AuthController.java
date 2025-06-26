package org.example.relations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.RegisterRequest;
import org.example.relations.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name = "Auth API", description = "Auth аккаунта")

public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register",  description = "Register now ", responses = {
            @ApiResponse(responseCode = "200", description = "Register successfully")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        authService.register(registerRequest);
        return ResponseEntity.ok("Register was successfully");
    }
    @Operation(summary = "Login",  description = "Login for what?", responses = {
            @ApiResponse(responseCode = "200", description = "Login successfully")
    })
    @GetMapping("/login")
    public ResponseEntity<?> login(Authentication authentication) {
        return ResponseEntity.ok("Login was successful " + authentication.getName());
    }
}
