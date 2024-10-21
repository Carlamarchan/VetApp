package com.vet.api.v1.owner.dtos;

/**
 * DTO that represents an owner response
 */
public class GetOwnerDto {

    private final Long id;
    private final String name;
    private final String lastName;
    private final String dni;
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
