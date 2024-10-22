package com.vet.api.v1.owner.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO that contains the information needed to create an owner
 */
public class CreateOwnerDto {

    @NotBlank(message = "Owner name is required.")
    private final String name;
    @NotBlank(message = "Owner last name is required.")
    private final String lastName;
    @NotBlank(message = "Owner DNI is required.")
    private final String dni;
    @NotBlank(message = "Owner phone is required.")
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
