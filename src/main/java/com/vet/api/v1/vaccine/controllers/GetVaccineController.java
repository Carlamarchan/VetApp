package com.vet.api.v1.vaccine.controllers;

import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.services.vaccine.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.VACCINE_TAG;

/**
 * Controller that retrieves a vaccine by its id
 */
@RestController
public class GetVaccineController {
    private final VaccineService vaccineService;

    @Autowired
    public GetVaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    /**
     * Retrieves a vaccine by a given id
     *
     * @param id Vaccine id
     * @return A response for an existent vaccine, otherwise a 404 error is returned
     */
    @Operation(
            summary = "Gets a vaccine by ID",
            tags = VACCINE_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Vaccine found",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "api/v1/vaccines/{id}")
    public ResponseEntity<GetVaccineDto> getVaccineById(@PathVariable Long id) {
        return this.vaccineService.getVaccineById(id);
    }

}
