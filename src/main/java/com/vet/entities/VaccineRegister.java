package com.vet.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table
public class VaccineRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
    @NotNull
    private Integer vaccineYear;

    public VaccineRegister(Pet pet, Vaccine vaccine, Integer vaccineYear) {
        this.pet = pet;
        this.vaccine = vaccine;
        this.vaccineYear = vaccineYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Integer getVaccineYear() {
        return vaccineYear;
    }

    public void setVaccineYear(Integer vaccineYear) {
        this.vaccineYear = vaccineYear;
    }
}
