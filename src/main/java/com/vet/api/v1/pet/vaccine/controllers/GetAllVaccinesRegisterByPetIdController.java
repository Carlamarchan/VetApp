package com.vet.api.v1.pet.vaccine.controllers;

import com.vet.api.v1.pet.vaccine.dtos.GetVaccineRegisterDto;
import com.vet.services.vaccineRegister.VaccineRegisterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.PET_TAG;

/**
 * Controller than retrieves all pet's vaccines
 */
@RestController
public class GetAllVaccinesRegisterByPetIdController {
    private final VaccineRegisterService vaccineRegisterService;

    @Autowired
    public GetAllVaccinesRegisterByPetIdController(VaccineRegisterService vaccineRegisterService) {
        this.vaccineRegisterService = vaccineRegisterService;
    }

    /**
     * Retrieves a page with pet's vaccines
     *
     * @param page Page information
     * @param id   Pet id
     * @return vaccines page
     */
    @Operation(
            summary = "Gets all pet's vaccines",
            tags = PET_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of paginated vaccines",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found",
                            content = @Content
                    )
            }
    )
    @GetMapping(path = "api/v1/pets/{id}/vaccines")
    public Page<GetVaccineRegisterDto> getAllVaccinesRegisterByPetId(@PageableDefault(size = 5) Pageable page, @PathVariable Long id) {
        return this.vaccineRegisterService.getAllVaccinesRegisterByPetId(page, id);
    }
}
