package com.vet.api.v1.pet.controllers;

import com.vet.services.owner.PetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeletePetControllerTest {
    @InjectMocks
    DeletePetController controller;

    @Mock
    PetService service;

    @Test
    public void testDeletePet() {
        Long petId = 1L;

        controller.deletePet(petId);
        verify(service, times(1)).deletePet(petId);
    }

}
