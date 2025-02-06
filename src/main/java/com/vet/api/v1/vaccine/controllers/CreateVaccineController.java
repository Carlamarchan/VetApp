package com.vet.api.v1.vaccine.controllers;

import com.vet.api.v1.vaccine.dtos.CreateVaccineDto;
import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.services.vaccine.VaccineService;
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

import static com.vet.api.constants.SwaggerConstants.VACCINE_TAG;

/**
 * Controller that creates a vaccine
 */
@RestController
public class CreateVaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public CreateVaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Creates a Vaccine
     *
     * @param vaccineDto DTO that contains the information needed to create a new vaccine
     * @return A response with the information of the created vaccine
     */
    @Operation(
            summary = "Crates a vaccine",
            tags = VACCINE_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Vaccine created successfully",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request",
                            content = @Content
                    )
            }
    )

    @PostMapping(path = "api/v1/vaccines")
    public ResponseEntity<GetVaccineDto> createVaccine(@RequestBody @Valid CreateVaccineDto vaccineDto) {
        return this.vaccineService.createVaccine(vaccineDto);
    }

}
