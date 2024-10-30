package com.vet.services.owner;

import com.vet.api.v1.pet.dtos.GetPetDto;
import com.vet.entities.Pet;
import com.vet.exception.PetNotFoundException;
import com.vet.mappers.PetMapper;
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

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
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
}
