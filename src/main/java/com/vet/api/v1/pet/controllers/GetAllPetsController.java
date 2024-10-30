package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.services.owner.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that retrieves all pets
 */
@RestController
public class GetAllPetsController {
    private final PetService petService;

    @Autowired
    public GetAllPetsController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Retrieves a page with pets
     *
     * @param page Page information
     * @return A pets page
     */
    @Operation(
            summary = "Gets all pets",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of paginated pets",
                            useReturnTypeSchema = true
                    )
            }
    )
    @GetMapping(path = "api/v1/pets")
    public Page<GetPetDto> getAllPets(@PageableDefault(size = 5) Pageable page) {
        return this.petService.getAllPets(page);
    }
}
