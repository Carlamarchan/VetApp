package com.vet.api.v1.vaccineRegister.dtos;

import com.vet.api.v1.vaccine.dtos.VaccineIdDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class CreateVaccineRegisterDto {
    @NotNull(message = "Vaccine year is required")
    @Schema(example = "2023")
    private final Integer year;
    @NotNull(message = "Vaccine is required")
    private final @Valid VaccineIdDto vaccine;

    public CreateVaccineRegisterDto(Integer year, VaccineIdDto vaccine) {
        this.year = year;
        this.vaccine = vaccine;
    }

    public int getYear() {
        return year;
    }

    public VaccineIdDto getVaccine() {
        return vaccine;
    }
}
