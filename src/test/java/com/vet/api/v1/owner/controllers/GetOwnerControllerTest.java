package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
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
public class GetOwnerControllerTest {


    @InjectMocks
    GetOwnerControler controller;

    @Mock
    OwnerService service;

    @Test
    public void testGetOwner() {
        GetOwnerDto expectedOwner = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );
        ResponseEntity<GetOwnerDto> expectedResponse = new ResponseEntity<>(
                expectedOwner,
                HttpStatus.OK
        );

        Long ownerId = 1L;

        when(service.getOwnerById(ownerId)).thenReturn(expectedResponse);

        ResponseEntity<GetOwnerDto> response = controller.getOwnerById(ownerId);
        assertEquals(expectedResponse, response);
    }
}

