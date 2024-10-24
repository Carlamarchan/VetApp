package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping(path = "api/v1/owners")
    public Page<GetOwnerDto> getAllOwnersDto(@PageableDefault(size = 5) Pageable page) {
        return this.ownerService.getAllOwnersDto(page);
    }
}
