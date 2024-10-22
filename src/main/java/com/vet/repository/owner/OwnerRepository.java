package com.vet.repository.owner;

import com.vet.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository related to Owner entity
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    /**
     * Retrieves an optional Owner by a given DNI
     *
     * @param dni DNI to search for the owner
     * @return An optional Owner if exists by the provided DNI
     */
    Optional<Owner> findByDni(String dni);
}
