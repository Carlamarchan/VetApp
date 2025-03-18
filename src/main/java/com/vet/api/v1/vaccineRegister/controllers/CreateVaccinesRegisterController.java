package com.vet.api.v1.vaccineRegister.controllers;

import com.vet.api.v1.pet.vaccineRegister.dtos.GetVaccineRegisterDto;
import com.vet.api.v1.vaccineRegister.dtos.CreateVaccineRegisterDto;
import com.vet.services.vaccineRegister.VaccineRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

@RestController
public class CreateVaccinesRegisterController {
    private final VaccineRegisterService vaccineRegisterService;

    @Autowired
    public CreateVaccinesRegisterController(VaccineRegisterService vaccineRegisterService) {
        this.vaccineRegisterService = vaccineRegisterService;
    }

    @Operation(
            summary = "Creates a vaccine Register",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Vaccine register created successfully",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @PostMapping(path = "api/v1/pets/{id}/vaccines-register")
    public ResponseEntity<GetVaccineRegisterDto> createVaccineRegister(
            @PathVariable Long id,
            @RequestBody @Valid CreateVaccineRegisterDto createVaccineRegisterDto
    ) {
        return this.vaccineRegisterService.createVaccineRegister(id, createVaccineRegisterDto);
    }
}
