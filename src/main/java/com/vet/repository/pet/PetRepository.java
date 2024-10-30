package com.vet.repository.pet;

import com.vet.entities.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to Pet entity
 */
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
}
