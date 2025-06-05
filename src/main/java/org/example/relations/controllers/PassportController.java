package org.example.relations.controllers;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.PassportDTO;
import org.example.relations.entity.Passport;
import org.example.relations.services.PassportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passport")
public class PassportController {
    private final PassportService passportService;

    @GetMapping
    public ResponseEntity<List<PassportDTO>> getPassports() {
        return ResponseEntity.ok().body(passportService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassportDTO> getPassportById(@PathVariable Long id) {
        return ResponseEntity.ok().body(passportService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PassportDTO> createPassport(@RequestBody PassportDTO passportDTO) {
        return ResponseEntity.ok().body(passportService.create(passportDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassportDTO> updatePassport(@PathVariable Long id, @RequestBody PassportDTO passportDTO) {
        return ResponseEntity.ok().body(passportService.update(id, passportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PassportDTO> deletePassport(@PathVariable Long id) {
        passportService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
