package com.vet.api.v1.pet.dtos;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.enums.PetType;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO that represents a pet response
 */
public class GetPetDto {
    private final Long id;
    @Schema (example = "Luna" )
    private final String name;
    @Schema (example = "DOG")
    private final PetType type;
    @Schema (example = "1111A")
    private final String  chipNumber;
    private final GetOwnerDto ownerDto ;


    public GetPetDto(Long id, String name, String chipNumber, PetType type, GetOwnerDto ownerDto) {
        this.id = id;
        this.name = name;
        this.chipNumber = chipNumber;
        this.type = type;
        this.ownerDto= ownerDto;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PetType getType() {
        return type;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public GetOwnerDto getOwnerDto() {
        return ownerDto;
    }
}

