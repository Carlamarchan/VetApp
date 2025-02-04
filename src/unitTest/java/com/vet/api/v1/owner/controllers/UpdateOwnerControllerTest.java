package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.api.v1.owner.dtos.UpdateOwnerDto;
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
public class UpdateOwnerControllerTest {

    @InjectMocks
    UpdateOwnerController controller;

    @Mock
    OwnerService service;

    @Test
    public void testUpdateOwner() {
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
        UpdateOwnerDto ownerRequest = new UpdateOwnerDto(
                "Luis",
                "Pérez",
                "0101A",
                "223344"
        );
        when(service.updateOwner(ownerId, ownerRequest)).thenReturn(expectedResponse);

        ResponseEntity<GetOwnerDto> response = controller.updateOwner(ownerId, ownerRequest);

        assertEquals(expectedResponse, response);
    }
}


