package org.example.relations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.AccountDTO;
import org.example.relations.entity.Account;
import org.example.relations.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Account")
@Tag(name = "Account API", description = "CRUD операции для аккаунта")
public class AccountController {
    private final AccountService AccountService;

    @Operation(summary = "Find all Accounts", tags = {"Account"}, description = "Find all Accounts for users", responses = {
            @ApiResponse(responseCode = "200", description = "Accounts found successfully")
    })
    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAccounts() {
        return ResponseEntity.ok().body(AccountService.getAll());
    }

    @Operation(summary = "Find Account", tags = {"Account"}, description = "Find Account by id", responses = {
            @ApiResponse(responseCode = "200", description = "Account was found successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")})
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok().body(AccountService.getById(id));
    }

    @Operation(summary = "Create Account", tags = {"Account"}, description = "Create new Account for users", responses = {
            @ApiResponse(responseCode = "200", description = "Account was created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO AccountDTO) {
        return ResponseEntity.ok().body(AccountService.create(AccountDTO));
    }

    @Operation(summary = "Update Account", tags = {"Account"}, description = "Update Account by id", responses = {
            @ApiResponse(responseCode = "200", description = "Account was updated successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")})
    @PutMapping("/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable Long id, @RequestBody AccountDTO AccountDTO) {
        return ResponseEntity.ok().body(AccountService.update(id, AccountDTO));
    }

    @Operation(summary = "Delete Account", tags = {"Account"}, description = "Delete Account by id", responses = {
            @ApiResponse(responseCode = "200", description = "Account was deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Account not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> deleteAccount(@PathVariable Long id) {
        AccountService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
