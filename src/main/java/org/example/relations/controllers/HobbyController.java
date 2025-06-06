package org.example.relations.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.HobbyDTO;
import org.example.relations.entity.Hobby;
import org.example.relations.services.HobbyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Hobby")
@Tag(name = "Hobby API", description = "CRUD операции для хобби")
public class HobbyController {
    private final HobbyService HobbyService;

    @Operation(summary = "Find all Hobbys", tags = {"Hobby"}, description = "Find all Hobbys for users", responses = {
            @ApiResponse(responseCode = "200", description = "Hobbys found successfully")
    })
    @GetMapping
    public ResponseEntity<List<HobbyDTO>> getHobbys() {
        return ResponseEntity.ok().body(HobbyService.getAll());
    }

    @Operation(summary = "Find Hobby", tags = {"Hobby"}, description = "Find Hobby by id", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was found successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby not found")})
    @GetMapping("/{id}")
    public ResponseEntity<HobbyDTO> getHobbyById(@PathVariable Long id) {
        return ResponseEntity.ok().body(HobbyService.getById(id));
    }

    @Operation(summary = "Create Hobby", tags = {"Hobby"}, description = "Create new Hobby for users", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<HobbyDTO> createHobby(@RequestBody HobbyDTO HobbyDTO) {
        return ResponseEntity.ok().body(HobbyService.create(HobbyDTO));
    }

    @Operation(summary = "Update Hobby", tags = {"Hobby"}, description = "Update Hobby by id", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was updated successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby not found")})
    @PutMapping("/{id}")
    public ResponseEntity<HobbyDTO> updateHobby(@PathVariable Long id, @RequestBody HobbyDTO HobbyDTO) {
        return ResponseEntity.ok().body(HobbyService.update(id, HobbyDTO));
    }

    @Operation(summary = "Delete Hobby", tags = {"Hobby"}, description = "Delete Hobby by id", responses = {
            @ApiResponse(responseCode = "200", description = "Hobby was deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Hobby not found")})
    @DeleteMapping("/{id}")
    public ResponseEntity<HobbyDTO> deleteHobby(@PathVariable Long id) {
        HobbyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
