package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.vet.api.constants.SwaggerConstants.OWNER_TAG;

/**
 * Controller that retrieves all owners
 */
@RestController
public class GetAllOwnersController {
    private final OwnerService ownerService;

    @Autowired
    public GetAllOwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Retrieves a page with owners
     *
     * @param page Page information
     * @return An owners page
     */
    @Operation(
            summary = "Gets all owners",
            tags = OWNER_TAG
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of paginated owners",
                            useReturnTypeSchema = true
                    )
            }
    )
    @GetMapping(path = "api/v1/owners")
    public Page<GetOwnerDto> getAllOwnersDto(@PageableDefault(size = 5) Pageable page) {
        return this.ownerService.getAllOwnersDto(page);
    }
}
