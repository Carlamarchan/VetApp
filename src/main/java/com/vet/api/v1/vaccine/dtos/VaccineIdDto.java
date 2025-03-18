package com.vet.api.v1.vaccine.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class VaccineIdDto {
    @Schema(example = "1")
    @NotNull(message = "Vaccine ID is required.")
    Long id;

    public VaccineIdDto() {
    }

    public VaccineIdDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
