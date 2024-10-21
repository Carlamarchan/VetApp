package com.vet.api.v1.owner.controllers;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.services.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that retrieves an owner by its id
 */
@RestController
public class GetOwnerControler {

    private final OwnerService ownerService;

    @Autowired
    public GetOwnerControler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Retrieves an owner by a given id
     *
     * @param id Owner id
     * @return A response for an existent owner, otherwise a 404 error is returned
     */
    @GetMapping(path = "api/v1/owners/{id}")
    public ResponseEntity<GetOwnerDto> getOwnerById(@PathVariable Long id) {
        return this.ownerService.getOwnerById(id);
    }
}
