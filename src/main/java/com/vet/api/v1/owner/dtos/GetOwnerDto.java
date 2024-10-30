package com.vet.api.v1.owner.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO that represents an owner response
 */
public class GetOwnerDto {

    @Schema(example = "1")
    private final Long id;
    @Schema(example = "Carla")
    private final String name;
    @Schema(example = "Marchán")
    private final String lastName;
    @Schema(example = "604333B")
    private final String dni;
    @Schema(example = "123456789")
    private final String phone;

    public GetOwnerDto(Long id, String name, String lastName, String dni, String phone) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDni() {
        return dni;
    }

    public String getPhone() {
        return phone;
    }
}
