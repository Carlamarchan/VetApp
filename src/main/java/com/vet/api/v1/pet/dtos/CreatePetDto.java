package com.vet.api.v1.pet.dtos;

import com.vet.api.v1.owner.dtos.OwnerIdDto;
import com.vet.enums.PetType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO that contains the information needed to create a pet
 */
public class CreatePetDto {
    @NotBlank(message = "Pet name is required.")
    @Schema(example = "Luna")
    private final String name;
    @NotBlank(message = "Pet chip number is required.")
    @Schema(example = "1111A")
    private final String chipNumber;
    @NotNull(message = "Pet type is required.")
    @Schema(example = "DOG")
    private final PetType type;
    @NotNull(message = "Owner is required.")
    private final @Valid OwnerIdDto owner;

    public CreatePetDto(String name, String chipNumber, PetType type, OwnerIdDto owner) {
        this.name = name;
        this.chipNumber = chipNumber;
        this.type = type;
        this.owner = owner;
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

    public OwnerIdDto getOwner() {
        return owner;
    }
}
