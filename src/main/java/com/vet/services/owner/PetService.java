package com.vet.services.owner;

import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.api.v1.pet.dtos.UpdatePetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;
import com.vet.exception.DuplicatedChipNumberException;
import com.vet.exception.OwnerNotFoundException;
import com.vet.exception.PetNotFoundException;
import com.vet.mappers.PetMapper;
import com.vet.repository.owner.OwnerRepository;
import com.vet.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Service related with pet's operations
 */
@Service
public class PetService {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    @Autowired
    public PetService(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    /**
     * Retrieves a pet by a given id
     *
     * @param id Pet id
     * @return A response for an existent pet, otherwise a 404 error is returned
     */
    public ResponseEntity<GetPetDto> getPetById(Long id) {
        Optional<Pet> optionalPet = petRepository.findById(id);
        if (optionalPet.isEmpty()) {
            throw new PetNotFoundException();
        }
        return new ResponseEntity<>(
                PetMapper.mapEntityToGetPetDto(optionalPet.get()),
                HttpStatus.OK
        );
    }

    /**
     * Creates a Pet
     *
     * @param petDto DTO that contains the information needed to create a pet
     * @return A response with the information of the created pet
     */
    public ResponseEntity<GetPetDto> createPet(CreatePetDto petDto) {
        Optional<Owner> retrievedOwner = ownerRepository.findById(petDto.getOwner().getId());
        if (retrievedOwner.isEmpty()) {
            throw new OwnerNotFoundException();
        }

        Optional<Pet> retrievedPet = petRepository.findByChipNumber(petDto.getChipNumber());
        if (retrievedPet.isPresent()) {
            throw new DuplicatedChipNumberException();
        }

        Owner foundOwner = retrievedOwner.get();
        Pet petToSave = PetMapper.mapCreatePetDtoToEntity(petDto, foundOwner);
        Pet savedPet = petRepository.save(petToSave);
        GetPetDto savedPetDto = PetMapper.mapEntityToGetPetDto(savedPet);
        return new ResponseEntity<>(
                savedPetDto,
                HttpStatus.CREATED
        );
    }

    /**
     * Updates a pet
     *
     * @param id     Pet Id
     * @param petDto DTO that contains the information needed to update a pet
     * @return A response with the information of the updated pet
     */
    public ResponseEntity<GetPetDto> updatePet(Long id, UpdatePetDto petDto) {
        Optional<Pet> retrievedPetById = petRepository.findById(id);
        if (retrievedPetById.isEmpty()) {
            throw new PetNotFoundException();
        }
        Optional<Pet> retrievePetByChipNumber = petRepository.findByChipNumber(petDto.getChipNumber());
        if (retrievePetByChipNumber.isPresent() && !Objects.equals(id, retrievePetByChipNumber.get().getId())) {
            throw new DuplicatedChipNumberException();
        }
        Pet petToUpdate = retrievedPetById.get();
        petToUpdate.setName(petDto.getName());
        petToUpdate.setType(petDto.getType());
        petToUpdate.setChipNumber(petDto.getChipNumber());

        Pet updatedPet = petRepository.save(petToUpdate);
        GetPetDto updatedPetDto = PetMapper.mapEntityToGetPetDto(updatedPet);
        return new ResponseEntity<>(
                updatedPetDto,
                HttpStatus.OK
        );
    }

    /**
     * Deletes a pet
     *
     * @param id Pet Id
     */
    public void deletePet(Long id) {
        Optional<Pet> retrievedPet = petRepository.findById(id);
        if (retrievedPet.isEmpty()) {
            throw new PetNotFoundException();
        }
        Pet petToDelete = retrievedPet.get();
        petRepository.delete(petToDelete);
    }

    /**
     * Retrieves a page with pets
     *
     * @param page Page information
     * @return A pets page
     */
    public Page<GetPetDto> getAllPets(Pageable page) {
        Page<Pet> petPage = petRepository.findAll(page);
        return petPage.map(PetMapper::mapEntityToGetPetDto);
    }
}
