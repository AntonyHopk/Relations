package org.example.relations.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PassportDTO(
        @NotNull
        @Size(min = 10, max = 12)
        String number) {
}
