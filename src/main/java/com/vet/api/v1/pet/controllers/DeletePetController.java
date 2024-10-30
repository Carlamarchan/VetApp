package com.vet.api.v1.pet.controllers;

import com.vet.services.owner.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that deletes a pet
 */
@RestController
public class DeletePetController {
    private final PetService petService;

    @Autowired
    public DeletePetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Deletes a pet
     *
     * @param id Pet ID
     */
    @Operation(
            summary = "Deletes a pet",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pet deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DeleteMapping(path = "api/v1/pets/{id}")
    public void deletePet(@PathVariable Long id) {
        this.petService.deletePet(id);
    }
}
