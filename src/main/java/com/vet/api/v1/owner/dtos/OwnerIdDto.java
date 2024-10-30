package com.vet.api.v1.owner.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * DTO that represents the owner's identifier
 */
public class OwnerIdDto {
    @Schema(example = "1" )
    @NotNull(message = "Owner ID is required.")
    Long id;

    public OwnerIdDto() {
    }

    public OwnerIdDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
