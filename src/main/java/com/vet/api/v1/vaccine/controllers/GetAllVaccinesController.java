package com.vet.api.v1.vaccine.controllers;

import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.services.vaccine.VaccineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.VACCINE_TAG;

/**
 * Controller that retrieves all vaccines
 */
@RestController
public class GetAllVaccinesController {
    private final VaccineService vaccineService;

    @Autowired
    public GetAllVaccinesController(VaccineService vaccineService){
        this.vaccineService = vaccineService;
    }
    @Operation(
            summary = "Gets all vaccines",
            tags = VACCINE_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of paginated vaccines",
                            useReturnTypeSchema = true
                    )
            }
    )
    @GetMapping(path = "api/v1/vaccines")
    public Page<GetVaccineDto>getAllVaccines(@PageableDefault(size = 3)Pageable page){
        return this.vaccineService.getAllVaccines(page);
    }

}
