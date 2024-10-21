package com.vet.repository.owner;

import com.vet.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository related to Owner entity
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
