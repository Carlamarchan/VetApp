package com.vet.api.v1.owner.controllers;

import com.vet.services.owner.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that deletes an owner
 */
@RestController
public class DeleteOwnerController {
    private final OwnerService ownerService;

    @Autowired
    public DeleteOwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    /**
     * Deletes an owner
     *
     * @param id Owner Id
     */
    @DeleteMapping(path = "api/v1/owners/{id}")
    public void deleteOwner(@PathVariable Long id) {
        this.ownerService.deleteOwner(id);
    }
}
