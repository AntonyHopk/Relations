package org.example.relations.services;

import org.example.relations.DTO.PassportDTO;

import java.util.List;

public interface PassportService {
    PassportDTO create(PassportDTO passportDTO);
    PassportDTO getById(Long id);
    List<PassportDTO> getAll();
    PassportDTO update(Long id,PassportDTO passportDTO);
    void delete(Long id);

}
