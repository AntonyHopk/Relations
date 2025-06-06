package org.example.relations.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.PassportDTO;
import org.example.relations.entity.Passport;
import org.example.relations.repositories.PassportRepository;
import org.example.relations.services.PassportService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PassportServiceImpl implements PassportService {
    private final PassportRepository repository;

    @Override
    public PassportDTO create(PassportDTO DTO) {
        Passport passport = Passport.builder().number(DTO.number()).build();
        return new PassportDTO(repository.save(passport).getNumber());
    }

    @Override
    public PassportDTO getById(Long id) {
        return repository.findById(id)
                .map(p -> new PassportDTO(p.getNumber()))
                .orElseThrow(() ->new NoSuchElementException("Passport not found"));
    }

    @Override
    public List<PassportDTO> getAll() {
        return repository.findAll().stream()
                .map(p -> new PassportDTO(p.getNumber()))
                .toList();
    }

    @Override
    public PassportDTO update(Long id, PassportDTO DTO) {
        Passport passport = repository.findById(id).orElseThrow(()->new NoSuchElementException("Passport not found"));
        passport.setNumber(DTO.number());
        return new PassportDTO(repository.save(passport).getNumber());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
