package com.vet.services.pet;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.api.v1.owner.dtos.OwnerIdDto;
import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.api.v1.pet.dtos.UpdatePetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;
import com.vet.enums.PetType;
import com.vet.exception.DuplicatedChipNumberException;
import com.vet.exception.OwnerNotFoundException;
import com.vet.exception.PetNotFoundException;
import com.vet.repository.owner.OwnerRepository;
import com.vet.repository.pet.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @InjectMocks
    PetService petService;

    @Mock
    PetRepository petRepository;

    @Mock
    OwnerRepository ownerRepository;

    @Test
    public void testGetPetByIdExceptions() {
        Long notExistentPetId = 2L;
        when(petRepository.findById(notExistentPetId)).thenReturn(Optional.empty());
        assertThrows(
                PetNotFoundException.class,
                () -> petService.getPetById(notExistentPetId)
        );
    }

    @Test
    public void testGetPetByIdOk() {
        Long existentPetId = 1L;

        GetOwnerDto petOwner = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        GetPetDto expectedPet = new GetPetDto(
                1L,
                "Luna",
                "1111A",
                PetType.DOG,
                petOwner
        );

        ResponseEntity<GetPetDto> expectedResponse = new ResponseEntity<>(
                expectedPet,
                HttpStatus.OK
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(1L);
        existentOwner.setDni("1111A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Marchán");
        existentOwner.setName("Carla");

        Pet existentPet = new Pet();
        existentPet.setId(1L);
        existentPet.setName("Luna");
        existentPet.setChipNumber("1111A");
        existentPet.setType(PetType.DOG);
        existentPet.setOwner(existentOwner);

        Optional<Pet> optionalExistentPet = Optional.of(existentPet);

        when(petRepository.findById(existentPetId)).thenReturn(optionalExistentPet);
        ResponseEntity<GetPetDto> response = petService.getPetById(existentPetId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testCreatePetExceptions() {
        OwnerIdDto noExistentOwnerId = new OwnerIdDto(
                1L
        );

        CreatePetDto requestPetWithNoExistentOwner = new CreatePetDto(
                "Luna",
                "1111A",
                PetType.DOG,
                noExistentOwnerId
        );

        when(ownerRepository.findById(noExistentOwnerId.getId())).thenReturn(Optional.empty());
        assertThrows(
                OwnerNotFoundException.class,
                () -> petService.createPet(requestPetWithNoExistentOwner)
        );

        OwnerIdDto existentOwnerId = new OwnerIdDto(
                2L
        );

        CreatePetDto requestPetWithDuplicatedChipNumber = new CreatePetDto(
                "Luna",
                "1111A",
                PetType.DOG,
                existentOwnerId
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(2L);
        existentOwner.setDni("1111A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Pérez");
        existentOwner.setName("Luis");

        Pet existentPet = new Pet();
        existentPet.setId(1L);
        existentPet.setName("Bruno");
        existentPet.setType(PetType.DOG);
        existentPet.setChipNumber("1111A");
        existentPet.setOwner(existentOwner);

        when(ownerRepository.findById(existentOwnerId.getId())).thenReturn(Optional.of(existentOwner));
        when(petRepository.findByChipNumber(requestPetWithDuplicatedChipNumber.getChipNumber())).thenReturn(Optional.of(existentPet));
        assertThrows(
                DuplicatedChipNumberException.class,
                () -> petService.createPet(requestPetWithDuplicatedChipNumber)
        );
    }

    @Test
    public void testCreatePetOk() {
        OwnerIdDto existentOwnerId = new OwnerIdDto(
                2L
        );

        CreatePetDto requestPetWithCorrectChipNumber = new CreatePetDto(
                "Luna",
                "1111A",
                PetType.DOG,
                existentOwnerId
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(2L);
        existentOwner.setDni("1111A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Pérez");
        existentOwner.setName("Luis");

        Pet savedPet = new Pet();
        savedPet.setId(1L);
        savedPet.setName("Luna");
        savedPet.setChipNumber("1111A");
        savedPet.setType(PetType.DOG);
        savedPet.setOwner(existentOwner);

        when(ownerRepository.findById(existentOwnerId.getId())).thenReturn(Optional.of(existentOwner));
        when(petRepository.findByChipNumber(requestPetWithCorrectChipNumber.getChipNumber()))
                .thenReturn(Optional.empty());
        when(petRepository.save(any(Pet.class))).thenReturn(savedPet);
        ResponseEntity<GetPetDto> response = petService.createPet(requestPetWithCorrectChipNumber);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedPet.getChipNumber(), response.getBody().getChipNumber());
        assertEquals(savedPet.getName(), response.getBody().getName());
        assertEquals(savedPet.getType(), response.getBody().getType());
        assertEquals(savedPet.getOwner().getDni(), response.getBody().getOwnerDto().getDni());
    }

    @Test
    public void testUpdatePetExceptions() {
        UpdatePetDto requestPet = new UpdatePetDto(
                "Luna",
                "1111A",
                PetType.DOG
        );

        Long notExistentPetId = 1L;

        when(petRepository.findById(notExistentPetId)).thenReturn(Optional.empty());
        assertThrows(
                PetNotFoundException.class,
                () -> petService.updatePet(notExistentPetId, requestPet));

        Long existentPetId = 1L;

        Pet existentPet = new Pet();
        existentPet.setId(1L);
        existentPet.setName("Luna");
        existentPet.setChipNumber("2222A");
        existentPet.setType(PetType.CAT);

        Pet dulicatedChipNumberPet = new Pet();
        dulicatedChipNumberPet.setId(2L);
        dulicatedChipNumberPet.setName("Bruno");
        dulicatedChipNumberPet.setChipNumber("1111A");
        dulicatedChipNumberPet.setType(PetType.CAT);

        when(petRepository.findById(existentPetId)).thenReturn(Optional.of(existentPet));
        when(petRepository.findByChipNumber(requestPet.getChipNumber())).
                thenReturn(Optional.of(dulicatedChipNumberPet));
        assertThrows(DuplicatedChipNumberException.class,
                () -> petService.updatePet(existentPetId, requestPet));
    }

    @Test
    public void testUpdatePetOk() {
        Long petId = 1L;

        UpdatePetDto requestPet = new UpdatePetDto(
                "Luna",
                "1111A",
                PetType.CAT
        );

        Pet existentPet = new Pet();
        existentPet.setId(1L);
        existentPet.setName("Luna");
        existentPet.setChipNumber("2222A");
        existentPet.setType(PetType.DOG);

        Owner ownerPet = new Owner();
        ownerPet.setId(2L);
        ownerPet.setDni("1111A");
        ownerPet.setPhone("123456");
        ownerPet.setLastName("Pérez");
        ownerPet.setName("Luis");

        Pet updatedPet = new Pet();
        updatedPet.setId(1L);
        updatedPet.setName("Luna");
        updatedPet.setChipNumber("1111A");
        updatedPet.setType(PetType.CAT);
        updatedPet.setOwner(ownerPet);

        when(petRepository.findById(petId)).thenReturn(Optional.of(existentPet));
        when(petRepository.findByChipNumber(requestPet.getChipNumber())).thenReturn(Optional.empty());
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        ResponseEntity<GetPetDto> response = petService.updatePet(petId, requestPet);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeletePetExceptions() {
        Long notExistentPet = 1L;

        when(petRepository.findById(notExistentPet)).thenReturn(Optional.empty());
        assertThrows(
                PetNotFoundException.class,
                () -> petService.deletePet(notExistentPet));
    }

    @Test
    public void testDeletePetOk() {
        Pet existentPet = new Pet();
        existentPet.setId(2L);
        existentPet.setName("Luna");
        existentPet.setChipNumber("2222A");
        existentPet.setType(PetType.DOG);
        Long existentPetId = 1L;

        when(petRepository.findById(existentPetId)).thenReturn(Optional.of(existentPet));
        petService.deletePet(existentPetId);
        verify(petRepository, times(1)).delete(existentPet);
    }

    @Test
    public void testGetAllPets() {
        Owner ownerPet1 = new Owner();
        ownerPet1.setId(1L);
        ownerPet1.setDni("1111A");
        ownerPet1.setPhone("123456");
        ownerPet1.setLastName("Marchán");
        ownerPet1.setName("Carla");

        Pet expectedPet1 = new Pet();
        expectedPet1.setId(1L);
        expectedPet1.setName("Bruno");
        expectedPet1.setChipNumber("1111A");
        expectedPet1.setType(PetType.DOG);
        expectedPet1.setOwner(ownerPet1);

        Owner ownerPet2 = new Owner();
        ownerPet2.setId(2L);
        ownerPet2.setDni("2222B");
        ownerPet2.setPhone("123456");
        ownerPet2.setLastName("Pérez");
        ownerPet2.setName("Luis");

        Pet expectedPet2 = new Pet();
        expectedPet2.setId(2L);
        expectedPet2.setName("Mordelón");
        expectedPet2.setChipNumber("2222A");
        expectedPet2.setType(PetType.CAT);
        expectedPet2.setOwner(ownerPet2);

        Owner ownerPet3 = new Owner();
        ownerPet3.setId(3L);
        ownerPet3.setDni("3333C");
        ownerPet3.setPhone("123456");
        ownerPet3.setLastName("Luisa");
        ownerPet3.setName("Parda");

        Pet expectedPet3 = new Pet();
        expectedPet3.setId(3L);
        expectedPet3.setName("Luna");
        expectedPet3.setChipNumber("3333A");
        expectedPet3.setType(PetType.FISH);
        expectedPet3.setOwner(ownerPet3);

        Pageable pagePet = PageRequest.of(0, 3);
        List<Pet> petList = List.of(expectedPet1, expectedPet2, expectedPet3);
        Page<Pet> resultPet = new PageImpl<>(petList, pagePet, 1);

        when(petRepository.findAll(pagePet)).thenReturn(resultPet);
        Page<GetPetDto> pageResponse = petService.getAllPets(pagePet);
        assertEquals(3L, pageResponse.getTotalElements());
    }

    @Test
    public void testUpdateOwnerPetExceptions() {
        Long notExistentPetId = 5L;
        Long ownerId = 3L;

        when(petRepository.findById(notExistentPetId)).thenReturn(Optional.empty());
        assertThrows(
                PetNotFoundException.class,
                () -> petService.updateOwnerPet(notExistentPetId, ownerId)
        );

        Owner ownerPet = new Owner();
        ownerPet.setId(1L);
        ownerPet.setDni("1111A");
        ownerPet.setPhone("123456");
        ownerPet.setLastName("Marchán");
        ownerPet.setName("Carla");

        Pet existentPet = new Pet();
        existentPet.setId(1L);
        existentPet.setName("Luna");
        existentPet.setChipNumber("2222A");
        existentPet.setType(PetType.DOG);
        existentPet.setOwner(ownerPet);

        Long existentPetId = 1L;

        Long notExistentOwnerId = 3L;

        when(petRepository.findById(existentPetId)).thenReturn(Optional.of(existentPet));
        when(ownerRepository.findById(notExistentOwnerId)).thenReturn(Optional.empty());
        assertThrows(
                OwnerNotFoundException.class,
                () -> petService.updateOwnerPet(existentPetId, notExistentOwnerId)
        );
    }

    @Test
    public void testUpdateOwnerPetOk() {
        Long petIdToBeUpdated = 1L;
        Long existentOwnerId = 2L;

        Owner previousOwner = new Owner();
        previousOwner.setId(1L);
        previousOwner.setDni("1111A");
        previousOwner.setPhone("123456");
        previousOwner.setLastName("Marchán");
        previousOwner.setName("Carla");

        Pet petToBeUpdated = new Pet();
        petToBeUpdated.setId(1L);
        petToBeUpdated.setName("Luna");
        petToBeUpdated.setChipNumber("2222A");
        petToBeUpdated.setType(PetType.DOG);
        petToBeUpdated.setOwner(previousOwner);

        Owner newOwner = new Owner();
        newOwner.setId(2L);
        newOwner.setDni("1111A");
        newOwner.setPhone("123456");
        newOwner.setLastName("Marchán");
        newOwner.setName("Carla");

        Pet updatedPet = new Pet();
        updatedPet.setId(1L);
        updatedPet.setName("Luna");
        updatedPet.setChipNumber("2222A");
        updatedPet.setType(PetType.DOG);
        updatedPet.setOwner(newOwner);

        when(petRepository.findById(petIdToBeUpdated)).thenReturn(Optional.of(petToBeUpdated));
        when(ownerRepository.findById(existentOwnerId)).thenReturn(Optional.of(newOwner));
        when(petRepository.save(any(Pet.class))).thenReturn(updatedPet);

        ResponseEntity<GetPetDto> response = petService.updateOwnerPet(petIdToBeUpdated, existentOwnerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}