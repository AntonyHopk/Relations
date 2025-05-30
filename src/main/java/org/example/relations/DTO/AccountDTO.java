package org.example.relations.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccountDTO(
        @NotNull
        @Size(min = 2, max = 50)
        String title) {

}
