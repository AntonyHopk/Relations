package org.example.relations.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.relations.DTO.AccountDTO;
import org.example.relations.entity.Account;
import org.example.relations.repositories.AccountRepository;
import org.example.relations.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;

    @Override
    public AccountDTO create(AccountDTO DTO) {
        Account acc = Account.builder().title(DTO.title()).build();
        return new AccountDTO(repository.save(acc).getTitle());
    }

    @Override
    public AccountDTO getById(Long id) {
        return repository.findById(id)
                .map(a -> new AccountDTO(a.getTitle()))
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }

    @Override
    public List<AccountDTO> getAll() {
        return repository.findAll().stream()
                .map(a -> new AccountDTO(a.getTitle()))
                .toList();
    }

    @Override
    public AccountDTO update(Long id, AccountDTO DTO) {
        Account acc = repository.findById(id).orElseThrow(() ->new NoSuchElementException("Account not found"));
        acc.setTitle(DTO.title());

        return new AccountDTO(repository.save(acc).getTitle());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}