package com.vet.api.v1.owner.controllers;

import com.vet.services.owner.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.OWNER_TAG;

/**
 * Controller that deletes an owner
 */
@RestController
public class DeleteOwnerController {
    private final OwnerService ownerService;

    @Autowired
    public DeleteOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Deletes an owner
     *
     * @param id Owner Id
     */
    @Operation(
            summary = "Deletes an owner",
            tags = OWNER_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Owner deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DeleteMapping(path = "api/v1/owners/{id}")
    public void deleteOwner(@PathVariable Long id) {
        this.ownerService.deleteOwner(id);
    }
}
