package com.vet.api.v1.pet.controllers;

import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.services.pet.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller that retrieves a pet by its id
 */
@RestController
public class GetPetController {
    private final PetService petService;

    @Autowired
    public GetPetController(PetService petservice) {
        this.petService = petservice;
    }

    /**
     * Retrieves a pet by a given id
     *
     * @param id Pet id
     * @return A response for an existent pet, otherwise a 404 error is returned
     */
    @Operation(
            summary = "Gets a pet by ID",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pet found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "api/v1/pets/{id}")
    public ResponseEntity<GetPetDto> getPetById(@PathVariable Long id) {
        return this.petService.getPetById(id);
    }
}
