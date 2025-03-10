package com.vet.api.v1.vaccineRegister.controllers;

import com.vet.services.vaccineRegister.VaccineRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.VACCINE_REGISTER_TAG;

/**
 * Controller that deletes a vaccine register by provided vaccine register ID.
 */
@RestController
public class DeleteVaccineRegisterController {
    private final VaccineRegisterService vaccineRegisterService;

    @Autowired
    public DeleteVaccineRegisterController(VaccineRegisterService vaccineRegisterService) {
        this.vaccineRegisterService = vaccineRegisterService;
    }

    /**
     * Deletes a vaccine register
     *
     * @param id Vaccine register ID
     */
    @Operation(
            summary = "Deletes a vaccine register",
            tags = VACCINE_REGISTER_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vaccine register deleted successfully"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DeleteMapping(path = "api/v1/vaccines-register/{id}")
    public void deleteVaccineRegister(@PathVariable Long id) {
        this.vaccineRegisterService.deleteVaccineRegister(id);
    }
}
