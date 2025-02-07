package com.vet.api.v1.vaccine.controllers;

import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.api.v1.vaccine.dtos.UpdateVaccineDto;
import com.vet.services.vaccine.VaccineService;
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

import static com.vet.api.constants.SwaggerConstants.VACCINE_TAG;

/**
 * Controller that updates a vaccine
 */
@RestController
public class UpdateVaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public UpdateVaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Updates a vaccine
     *
     * @param vaccineId  Vaccine Id
     * @param vaccineDto DTO that contains the information needed to update a vaccine
     * @return A response with the information of the updated vaccine
     */
    @Operation(
            summary = "Updates a vaccine",
            tags = VACCINE_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Updated Vaccine",
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
    @PutMapping(path = "api/v1/vaccines/{vaccineId}")
    public ResponseEntity<GetVaccineDto> updateVaccine(@PathVariable Long vaccineId, @RequestBody @Valid UpdateVaccineDto vaccineDto) {
        return this.vaccineService.updateVaccine(vaccineId, vaccineDto);
    }

}
