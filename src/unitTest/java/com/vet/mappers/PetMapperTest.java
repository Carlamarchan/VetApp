package com.vet.mappers;

import com.vet.api.v1.owner.dtos.OwnerIdDto;
import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;
import com.vet.enums.PetType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PetMapperTest {

    @Test
    public void testEntityToGetPetDto() {
        Owner ownerToBeMapped = new Owner();
        ownerToBeMapped.setId(1L);
        ownerToBeMapped.setDni("1111A");
        ownerToBeMapped.setPhone("123456");
        ownerToBeMapped.setLastName("Marchán");
        ownerToBeMapped.setName("Carla");

        Pet petToMap = new Pet();
        petToMap.setId(1L);
        petToMap.setName("Luna");
        petToMap.setChipNumber("1111A");
        petToMap.setType(PetType.DOG);
        petToMap.setOwner(ownerToBeMapped);

        GetPetDto mappedPetDto = PetMapper.mapEntityToGetPetDto(petToMap);

        assertEquals(petToMap.getId(), mappedPetDto.getId());
        assertEquals(petToMap.getName(), mappedPetDto.getName());
        assertEquals(petToMap.getChipNumber(), mappedPetDto.getChipNumber());
        assertEquals(petToMap.getType(), mappedPetDto.getType());
        assertEquals(petToMap.getOwner().getId(), mappedPetDto.getOwner().getId());
        assertEquals(petToMap.getOwner().getName(), mappedPetDto.getOwner().getName());
        assertEquals(petToMap.getOwner().getLastName(), mappedPetDto.getOwner().getLastName());
        assertEquals(petToMap.getOwner().getDni(), mappedPetDto.getOwner().getDni());
        assertEquals(petToMap.getOwner().getPhone(), mappedPetDto.getOwner().getPhone());
    }

    @Test
    public void testCreatePetDtoToEntity() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setDni("1111A");
        owner.setPhone("123456");
        owner.setLastName("Marchán");
        owner.setName("Carla");

        OwnerIdDto ownerId = new OwnerIdDto(
                owner.getId()
        );

        CreatePetDto petDtoToBeMapped = new CreatePetDto(
                "Bruno",
                "1111A",
                PetType.DOG,
                ownerId
        );

        Pet mappedPet = PetMapper.mapCreatePetDtoToEntity(petDtoToBeMapped, owner);

        assertEquals(petDtoToBeMapped.getName(), mappedPet.getName());
        assertEquals(petDtoToBeMapped.getChipNumber(), mappedPet.getChipNumber());
        assertEquals(petDtoToBeMapped.getType(), mappedPet.getType());
        assertEquals(owner, mappedPet.getOwner());
    }
}
