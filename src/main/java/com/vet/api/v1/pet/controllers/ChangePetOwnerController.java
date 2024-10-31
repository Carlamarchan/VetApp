package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.services.owner.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that updates a pet owner
 */
@RestController
public class ChangePetOwnerController {
    private final PetService petService;

    @Autowired
    public ChangePetOwnerController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Updates a pet owner
     *
     * @param petId   Pet ID
     * @param ownerId Owner ID
     * @return A response with the information of the updated pet owner
     */
    @Operation(
            summary = "Updates a pet owner",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated pet owner",
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
    @PutMapping(path = "api/v1/pets/{petId}/change_owner")
    public ResponseEntity<GetPetDto> updateOwnerPet(@PathVariable Long petId, @RequestParam Long ownerId) {
        return this.petService.updateOwnerPet(petId, ownerId);
    }
}
