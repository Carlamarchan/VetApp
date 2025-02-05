package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.CreatePetDto;
import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
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

import static com.vet.api.constants.SwaggerConstants.OWNER_TAG;

/**
 * Controller that creates an owner
 */
@RestController
public class CreateOwnerController {

    private final OwnerService ownerService;

    @Autowired
    public CreateOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Creates an owner
     *
     * @param ownerDto DTO that contains the information needed to create an owner
     * @return A response with the information of the created owner
     */
    @Operation(
            summary = "Creates an owner",
            tags = OWNER_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Owner created successfully",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content
                    )
            }
    )
    @PostMapping(path = "api/v1/owners")
    public ResponseEntity<GetOwnerDto> createOwner(@RequestBody @Valid CreatePetDto ownerDto) {
        return this.ownerService.createOwner(ownerDto);
    }
}

