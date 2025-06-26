package org.example.relations.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.RegisterRequest;
import org.example.relations.entity.Credentials;
import org.example.relations.entity.Role;
import org.example.relations.entity.User;
import org.example.relations.repositories.CredentialsRepository;
import org.example.relations.repositories.RoleRepository;
import org.example.relations.repositories.UserRepository;
import org.example.relations.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.name());
        user.setAge(registerRequest.age());

        Role role = roleRepository.findByName("ROLE_USER").orElseThrow(()->new RuntimeException("Role not found"));

        Credentials credential = new Credentials();
        credential.setUsername(registerRequest.name());
        credential.setPassword(passwordEncoder.encode(registerRequest.password()));
        credential.setRole(role);
        credential.setUser(user);
//        userRepository.save(user);
        credentialsRepository.save(credential);
    }
}
