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
    Optional<Pet> findByChipNumber(String chipNumber);
}
