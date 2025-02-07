package com.vet.api.v1.vaccine.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO that contains the information needed to update a vaccine
 */
public class UpdateVaccineDto {
    @NotBlank(message = "Vaccine name is required.")
    @Schema(example = "Rabies")
    private String name;

    public UpdateVaccineDto() {
    }

    public UpdateVaccineDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
