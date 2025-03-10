package com.vet.api.v1.pet.vaccineRegister.dtos;

import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO that represents a vaccine register response
 */
public class GetVaccineRegisterDto {
    @Schema(example = "1")
    private final Long id;
    private final GetVaccineDto vaccine;
    @Schema(example = "2023")
    private final int year;

    public GetVaccineRegisterDto(Long id, GetVaccineDto vaccine, int year) {
        this.id = id;
        this.vaccine = vaccine;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public GetVaccineDto getVaccine() {
        return vaccine;
    }

    public int getYear() {
        return year;
    }
}

