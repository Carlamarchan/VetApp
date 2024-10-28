package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.OWNER_TAG;

/**
 * Controller that retrieves an owner by its id
 */
@RestController
public class GetOwnerControler {

    private final OwnerService ownerService;

    @Autowired
    public GetOwnerControler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Retrieves an owner by a given id
     *
     * @param id Owner id
     * @return A response for an existent owner, otherwise a 404 error is returned
     */
    @Operation(
            summary = "Gets an owner by ID",
            tags = OWNER_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Found owner",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "api/v1/owners/{id}")
    public ResponseEntity<GetOwnerDto> getOwnerById(@PathVariable Long id) {
        return this.ownerService.getOwnerById(id);
    }
}
