package org.example.relations.services;

import org.example.relations.DTO.AccountDTO;

import java.util.List;

public interface AccountService {

        AccountDTO create(AccountDTO dto);
        AccountDTO getById(Long id);
        List<AccountDTO> getAll();
        AccountDTO update(Long id, AccountDTO dto);
        void delete(Long id);

}
