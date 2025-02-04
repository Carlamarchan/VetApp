package com.vet.api.v1.owner.controllers;

import com.vet.services.owner.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteOwnerControllerTest {

    @InjectMocks
    DeleteOwnerController controller;

    @Mock
    OwnerService service;

    @Test
    public void testDeleteOwner() {
        Long ownerId = 1L;

        controller.deleteOwner(ownerId);
        verify(service, times(1)).deleteOwner(ownerId);
    }
}
