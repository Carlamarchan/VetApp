package com.vet.api.v1.vaccine.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO that contains the information needed to create a vaccine
 */
public class CreateVaccineDto {
    @NotBlank(message = "Vaccine name is required.")
    @Schema(example = "Rabies")
    private String name;

    public CreateVaccineDto() {
    }

    public CreateVaccineDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
