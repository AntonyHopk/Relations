package org.example.relations.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.JwtResponse;
import org.example.relations.DTO.LoginRequest;
import org.example.relations.DTO.RefreshRequest;
import org.example.relations.DTO.RegisterRequest;
import org.example.relations.config.JwtUtils;
import org.example.relations.entity.Credentials;
import org.example.relations.entity.Role;
import org.example.relations.entity.User;
import org.example.relations.repositories.CredentialsRepository;
import org.example.relations.repositories.RoleRepository;
import org.example.relations.repositories.UserRepository;
import org.example.relations.services.AuthService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setAge(request.age());

        Role role = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Credentials credentials = new Credentials();
        credentials.setUsername(request.username());
        credentials.setPassword(passwordEncoder.encode(request.password()));
        credentials.setRole(role);
        credentials.setUser(user);

        userRepository.save(user);
        credentialsRepository.save(credentials);
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Credentials credentials = credentialsRepository.findByUserName(request.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), credentials.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        String access = jwtUtils.generateAccessToken(request.username());
        String refresh = jwtUtils.generateRefreshToken(request.username());

        return new JwtResponse(access, refresh);
    }

    @Override
    public JwtResponse refreshToken(RefreshRequest request) {
        if (!jwtUtils.isTokenValid(request.refreshToken())) {
            throw new RuntimeException("Invalid refresh token");
        }
        String username = jwtUtils.extractUsername(request.refreshToken());
        String newAccess = jwtUtils.generateAccessToken(username);
        return new JwtResponse(newAccess, request.refreshToken());
    }
}