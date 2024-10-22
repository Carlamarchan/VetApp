package com.vet.mappers;

import com.vet.api.v1.owner.dtos.CreateOwnerDto;
import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.entities.Owner;

/**
 * Mapper that processes the conversions between the Owner entity and its DTOs and vice versa
 */
public class OwnerMapper {

    /**
     * Maps an entity to a GetOwnerDto
     *
     * @param entity An owner entity
     * @return A mapped GetOwnerDto
     */
    public static GetOwnerDto mapEntityToGetOwnerDto(Owner entity) {
        return new GetOwnerDto(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getDni(),
                entity.getPhone()
        );
    }

    /**
     * Maps a GetOwnerDto to an entity
     *
     * @param createOwnerDto A GetOwnerDto object
     * @return A mapped entity
     */
    public static Owner mapCreateOwnerDtoToEntity(CreateOwnerDto createOwnerDto) {
        return new Owner(
                createOwnerDto.getLastName(),
                createOwnerDto.getDni(),
                createOwnerDto.getPhone(),
                createOwnerDto.getName()
        );
    }

}
