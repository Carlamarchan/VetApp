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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetAllPetsControllerTest {

    @InjectMocks
    GetAllPetsController controller;

    @Mock
    PetService service;
    @Test
    public void testGetAllPets() {
        GetOwnerDto expectedOwner1 = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );
        GetPetDto expectedPet1 = new GetPetDto(
                1L,
                "Bruno",
                "1111A",
                PetType.DOG,
                expectedOwner1
        );

        GetOwnerDto expectedOwner2 = new GetOwnerDto(
                2L,
                "Luis",
                "Pérez",
                "2222B",
                "123456"
        );
        GetPetDto expectedPet2 = new GetPetDto(
                1L,
                "Bruno",
                "1111A",
                PetType.DOG,
                expectedOwner2
        );

        GetOwnerDto expectedOwner3 = new GetOwnerDto(
                3L,
                "Luisa",
                "Parda",
                "3333C",
                "123456"
        );
        GetPetDto expectedPet3 = new GetPetDto(
                1L,
                "Bruno",
                "1111A",
                PetType.DOG,
                expectedOwner3
        );

        Page<GetPetDto> expectedPage = new PageImpl<>(List.of(expectedPet1, expectedPet2, expectedPet3));
        Pageable page = PageRequest.of(0, 3);

        when(service.getAllPets(page)).thenReturn(expectedPage);

        Page<GetPetDto> pageResponse = controller.getAllPets(page);
        assertEquals(expectedPage, pageResponse);
    }

}
