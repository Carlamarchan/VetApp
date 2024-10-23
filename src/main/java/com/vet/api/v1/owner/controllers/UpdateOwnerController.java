package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.api.v1.owner.dtos.UpdateOwnerDto;
import com.vet.services.owner.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that updates an owner
 */
@RestController
public class UpdateOwnerController {
    private final OwnerService ownerService;

    @Autowired
    public UpdateOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Updates an Owner
     *
     * @param id       Owner id
     * @param ownerDto DTO that contains the information needed to update an owner
     * @return A response with the information of the updated owner
     */
    @PutMapping(path = "api/v1/owners/{id}")
    public ResponseEntity<GetOwnerDto> updateOwner(@PathVariable Long id, @RequestBody @Valid UpdateOwnerDto ownerDto) {
        return this.ownerService.updateOwner(id, ownerDto);
    }
}
