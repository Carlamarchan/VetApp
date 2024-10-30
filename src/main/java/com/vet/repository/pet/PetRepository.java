package com.vet.repository.pet;

import com.vet.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository related to Pet entity
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    /**
     * Retrieves an optional pet by a given chip number
     *
     * @param chipNumber Chip number to search for the pet
     * @return An optional pet if exists by the provided chip number
     */
    Optional<Pet> findByChipNumber(String chipNumber);
}
