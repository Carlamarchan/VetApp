package com.vet.api.v1.vaccine.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO that represents a vaccine response
 */
public class GetVaccineDto {
    @Schema(example = "1")
    private final Long id;
    @Schema(example = "Rabies")
    private final String name;

    public GetVaccineDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
