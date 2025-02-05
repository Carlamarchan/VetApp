package com.vet.mappers;

import com.vet.api.v1.owner.dtos.CreatePetDto;
import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.entities.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OwnerMapperTest {

    @Test
    public void testMapEntityToGetOwnerDto() {
        Owner ownerToBeMapped = new Owner();
        ownerToBeMapped.setId(1L);
        ownerToBeMapped.setName("Carla");
        ownerToBeMapped.setLastName("Marchán");
        ownerToBeMapped.setDni("1111A");
        ownerToBeMapped.setPhone("123456");

        GetOwnerDto mappedOwnerDto = OwnerMapper.mapEntityToGetOwnerDto(ownerToBeMapped);

        assertEquals(ownerToBeMapped.getId(), mappedOwnerDto.getId());
        assertEquals(ownerToBeMapped.getDni(), mappedOwnerDto.getDni());
        assertEquals(ownerToBeMapped.getName(), mappedOwnerDto.getName());
        assertEquals(ownerToBeMapped.getLastName(), mappedOwnerDto.getLastName());
        assertEquals(ownerToBeMapped.getPhone(), mappedOwnerDto.getPhone());
    }

    @Test
    public void testMapCreateOwnerDtoToEntity() {
        CreatePetDto ownerDtoToBeMapped = new CreatePetDto(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        Owner mappedOwner = OwnerMapper.mapCreateOwnerDtoToEntity(ownerDtoToBeMapped);

        assertEquals(ownerDtoToBeMapped.getDni(), mappedOwner.getDni());
        assertEquals(ownerDtoToBeMapped.getName(), mappedOwner.getName());
        assertEquals(ownerDtoToBeMapped.getLastName(), mappedOwner.getLastName());
        assertEquals(ownerDtoToBeMapped.getPhone(), mappedOwner.getPhone());
    }
}

