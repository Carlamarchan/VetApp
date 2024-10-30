package com.vet.api.v1.pet.dtos;

import com.vet.enums.PetType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO that contains the information needed to update a pet
 */
public class UpdatePetDto {

    @NotBlank(message = "Pet name is required.")
    @Schema(example = "Luna")
    private final String name;
    @NotBlank(message = "Pet chip number is required.")
    @Schema(example = "1111A")
    private final String chipNumber;
    @NotNull(message = "Pet type is required.")
    @Schema(example = "DOG")
    private final PetType type;

    public UpdatePetDto(String name, String chipNumber, PetType type) {
        this.name = name;
        this.chipNumber = chipNumber;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public PetType getType() {
        return type;
    }
}

