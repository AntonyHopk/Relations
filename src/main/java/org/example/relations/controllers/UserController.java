package org.example.relations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.UserDTO;
import org.example.relations.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/User")
@Tag(name = "User API", description = "CRUD операции для юзера")
public class UserController {
    private final UserService UserService;

    @Operation(summary = "Find all Users", tags = {"User"}, description = "Find all Users for users", responses = {
            @ApiResponse(responseCode = "200", description = "Users found successfully")
    })
    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(UserService.getAll());
    }

    @Operation(summary = "Find User", tags = {"User"}, description = "Find User by id", responses = {
            @ApiResponse(responseCode = "200", description = "User was found successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(UserService.getById(id));
    }

    @Operation(summary = "Create User", tags = {"User"}, description = "Create new User for users", responses = {
            @ApiResponse(responseCode = "200", description = "User was created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO UserDTO) {
        return ResponseEntity.ok().body(UserService.create(UserDTO));
    }

    @Operation(summary = "Update User", tags = {"User"}, description = "Update User by id", responses = {
            @ApiResponse(responseCode = "200", description = "User was updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO UserDTO) {
        return ResponseEntity.ok().body(UserService.update(id, UserDTO));
    }

    @Operation(summary = "Delete User", tags = {"User"}, description = "Delete User by id", responses = {
            @ApiResponse(responseCode = "200", description = "User was deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        UserService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
