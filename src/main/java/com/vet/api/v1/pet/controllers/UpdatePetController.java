package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.api.v1.pet.dtos.UpdatePetDto;
import com.vet.services.owner.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that updates a pet
 */
@RestController
public class UpdatePetController {
    private final PetService petService;

    @Autowired
    public UpdatePetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Updates a pet
     *
     * @param id     Pet ID
     * @param petDto DTO that contains the information needed to update a pet
     * @return A response with the information of the updated pet
     */
    @Operation(
            summary = "Updates a pet by ID",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated pet",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @PutMapping(path = "api/v1/pets/{id}")
    public ResponseEntity<GetPetDto> updatePet(@PathVariable Long id, @RequestBody @Valid UpdatePetDto petDto) {
        return this.petService.updatePet(id, petDto);
    }
}
