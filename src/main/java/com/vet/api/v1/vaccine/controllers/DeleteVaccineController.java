package com.vet.api.v1.vaccine.controllers;

import com.vet.services.vaccine.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.VACCINE_TAG;

/**
 * Controller that deletes a vaccine
 */
@RestController
public class DeleteVaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public DeleteVaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Deletes a vaccine
     * @param id Vaccine ID
     */
    @Operation(
            summary = "Deletes a vaccine",
            tags = VACCINE_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vaccine deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DeleteMapping(path = "api/v1/vaccines/{id}")
    public void deleteVaccine(@PathVariable Long id) {
        this.vaccineService.deleteVaccine(id);
    }
}
