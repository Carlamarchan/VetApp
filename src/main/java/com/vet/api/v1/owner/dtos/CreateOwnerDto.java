package com.vet.api.v1.owner.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO that contains the information needed to create an owner
 */
public class CreateOwnerDto {

    @NotBlank(message = "Owner name is required.")
    @Schema(example = "Carla")
    private final String name;
    @NotBlank(message = "Owner last name is required.")
    @Schema(example = "Marchán")
    private final String lastName;
    @NotBlank(message = "Owner DNI is required.")
    @Schema(example = "604333B")
    private final String dni;
    @NotBlank(message = "Owner phone is required.")
    @Schema(example = "123456789")
    private final String phone;

    public CreateOwnerDto(String name, String lastName, String dni, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
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
