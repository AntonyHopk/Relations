package org.example.relations.services;

import org.example.relations.DTO.HobbyDTO;

import java.util.List;

public interface HobbyService {
    
        HobbyDTO create(HobbyDTO DTO);
        HobbyDTO getById(Long id);
        List<HobbyDTO> getAll();
        HobbyDTO update(Long id, HobbyDTO DTO);
        void delete(Long id);
    }

