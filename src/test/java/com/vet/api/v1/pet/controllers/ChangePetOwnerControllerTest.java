package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.enums.PetType;
import com.vet.services.pet.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChangePetOwnerControllerTest {
    @InjectMocks
    ChangePetOwnerController controller;

    @Mock
    PetService service;

    @Test
    public void testChangePetOwner() {
        GetOwnerDto expectedOwner = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );
        GetPetDto expectedPet = new GetPetDto(
                1L,
                "Bruno",
                "1111A",
                PetType.DOG,
                expectedOwner
        );
        ResponseEntity<GetPetDto> expectedResponse = new ResponseEntity<>(
                expectedPet,
                HttpStatus.OK
        );

        Long petId = 1L;
        Long ownerId = 2L;

        when(service.updateOwnerPet(petId, ownerId)).thenReturn(expectedResponse);

        ResponseEntity<GetPetDto> response = controller.updateOwnerPet(petId, ownerId);

        assertEquals(expectedResponse, response);
    }
}

