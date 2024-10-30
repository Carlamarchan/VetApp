package com.vet.services.owner;

import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;
import com.vet.exception.DuplicatedChipNumberException;
import com.vet.exception.OwnerNotFoundException;
import com.vet.exception.PetNotFoundException;
import com.vet.mappers.PetMapper;
import com.vet.repository.owner.OwnerRepository;
import com.vet.repository.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
