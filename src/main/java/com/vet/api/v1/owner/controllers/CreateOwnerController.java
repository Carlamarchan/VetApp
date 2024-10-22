package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.CreateOwnerDto;
import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that creates an owner
 */
@RestController
public class CreateOwnerController {

    private final OwnerService ownerService;

    @Autowired
    public CreateOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Creates an owner
     *
     * @param ownerDto DTO that contains the information needed to create an owner
     * @return A response with the information of the created owner
     */
    @PostMapping(path = "api/v1/owners")
    public ResponseEntity<GetOwnerDto> createOwner(@RequestBody @Valid CreateOwnerDto ownerDto) {
        return this.ownerService.createOwner(ownerDto);
    }
}

