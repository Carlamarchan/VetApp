package com.vet.repository.vaccine;

import com.vet.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to Vaccine entity
 */
@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}

