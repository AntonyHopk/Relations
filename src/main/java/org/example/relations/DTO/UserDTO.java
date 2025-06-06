package org.example.relations.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.relations.entity.Passport;

import java.util.List;
import java.util.Set;

public record UserDTO(
        @NotNull
        @Size(min = 2, max = 50)
        String name,

        @NotNull
        @Min(18)
        @Max(100)
        int age,

        @NotNull
        Passport passport,
        List<AccountDTO> accounts,
        Set<String> hobbies
) {
}
