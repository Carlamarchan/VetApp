package com.vet.mappers;

import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;

public class PetMapper {

    /**
     * Maps an entity to a GetPetDto
     *
     * @param entity A pet entity
     * @return A mapped GetPetDto
     */
    public static GetPetDto mapEntityToGetPetDto(Pet entity) {
        return new GetPetDto(
                entity.getId(),
                entity.getName(),
                entity.getChipNumber(),
                entity.getType(),
                OwnerMapper.mapEntityToGetOwnerDto(entity.getOwner())
        );
    }

    /**
     * Maps a GetPetDto and its Owner to an entity
     *
     * @param createPetDto A GetPetDto object
     * @param foundOwner   The pet's owner
     * @return A mapped entity
     */
    public static Pet mapCreatePetDtoToEntity(CreatePetDto createPetDto, Owner foundOwner) {
        return new Pet(
                createPetDto.getName(),
                createPetDto.getChipNumber(),
                createPetDto.getType(),
                foundOwner
        );
    }

}
