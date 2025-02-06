package com.vet.repository.vaccine;

import com.vet.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository related to Vaccine entity
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    /**
     * Retrieves an optional vaccine by a given name
     *
     * @param name name to search for the vaccine
     * @return An optional vaccine if exists by the provided name
     */
    Optional<Vaccine> findByName(String name);
}

