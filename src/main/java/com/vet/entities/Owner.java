package com.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Owner entity
 */
@Entity
@Table
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @Column(unique = true)
    @NotBlank
    private String dni;
    @NotBlank
    private String phone;

    public Owner() {
    }

    public Owner(String lastName, String dni, String phone, String name) {
        this.lastName = lastName;
        this.dni = dni;
        this.phone = phone;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
