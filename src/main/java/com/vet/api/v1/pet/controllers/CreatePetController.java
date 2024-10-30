package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.services.owner.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that creates a pet
 */
@RestController
public class CreatePetController {
    private final PetService petService;

    @Autowired

    public CreatePetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Creates a Pet
     *
     * @param petDto DTO that contains the information needed to create a new pet
     * @return A response with the information of the created pet
     */
    @Operation(
            summary = "Creates a pet",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pet created successfully",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @PostMapping(path = "api/v1/pets")
    public ResponseEntity<GetPetDto> createPet(@RequestBody @Valid CreatePetDto petDto) {
        return this.petService.createPet(petDto);
    }
}