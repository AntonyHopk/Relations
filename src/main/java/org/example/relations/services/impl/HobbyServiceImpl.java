package org.example.relations.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.HobbyDTO;
import org.example.relations.entity.Hobby;
import org.example.relations.repositories.HobbyRepository;
import org.example.relations.services.HobbyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class HobbyServiceImpl implements HobbyService {
    private final HobbyRepository repository;

    @Override
    public HobbyDTO create(HobbyDTO DTO) {
        return new HobbyDTO(repository.save(Hobby.builder().type(DTO.type()).build()).getType());
    }

    @Override
    public HobbyDTO getById(Long id) {
        return repository.findById(id)
                .map(h -> new HobbyDTO(h.getType()))
                .orElseThrow(() -> new NoSuchElementException("Hobby not found"));
    }

    @Override
    public List<HobbyDTO> getAll() {
        return repository.findAll().stream()
                .map(h -> new HobbyDTO(h.getType()))
                .toList();
    }

    @Override
    public HobbyDTO update(Long id, HobbyDTO DTO) {
        Hobby hobby = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Hobby not found"));
        hobby.setType(DTO.type());
        return new HobbyDTO(repository.save(hobby).getType());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
