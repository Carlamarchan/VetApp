package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
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
public class GetAllOwnersControllerTest {

    @InjectMocks
    GetAllOwnersController controller;

    @Mock
    OwnerService service;

    @Test
    public void testGetAllOwners() {
        GetOwnerDto expectedOwner1 = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );
        GetOwnerDto expectedOwner2 = new GetOwnerDto(
                2L,
                "Luis",
                "Pérez",
                "2222B",
                "123456"
        );
        GetOwnerDto expectedOwner3 = new GetOwnerDto(
                3L,
                "Luisa",
                "Parda",
                "3333C",
                "123456"
        );

        Page<GetOwnerDto> expectedPage = new PageImpl<>(List.of(expectedOwner1, expectedOwner2, expectedOwner3));
        Pageable page = PageRequest.of(0, 3);

        when(service.getAllOwners(page)).thenReturn(expectedPage);

        Page<GetOwnerDto> pageResponse = controller.getAllOwners(page);
        assertEquals(expectedPage, pageResponse);
    }
}
