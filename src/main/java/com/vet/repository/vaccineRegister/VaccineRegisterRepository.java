package com.vet.repository.vaccineRegister;

import com.vet.entities.Pet;
import com.vet.entities.VaccineRegister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRegisterRepository extends JpaRepository<VaccineRegister, Long> {

    /**
     * Retrieves a page with all pet's vaccines register
     *
     * @param pet Pet to get its vaccinations from
     * @param page Page to retrieve vaccines register
     * @return A page with all pet's vaccines register.
     */
    Page<VaccineRegister> findByPet(Pet pet, Pageable page);
}
