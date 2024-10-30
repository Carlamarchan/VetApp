package com.vet.repository.owner;

import com.vet.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository related to owner entity
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    /**
     * Retrieves an optional owner by a given DNI
     *
     * @param dni DNI to search for the owner
     * @return An optional owner if exists by the provided DNI
     */
    Optional<Owner> findByDni(String dni);
}
