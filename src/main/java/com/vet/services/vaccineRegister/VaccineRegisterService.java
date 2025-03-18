package com.vet.services.vaccineRegister;

import com.vet.api.v1.pet.vaccineRegister.dtos.GetVaccineRegisterDto;
import com.vet.api.v1.vaccineRegister.dtos.CreateVaccineRegisterDto;
import com.vet.entities.Pet;
import com.vet.entities.Vaccine;
import com.vet.entities.VaccineRegister;
import com.vet.exception.PetNotFoundException;
import com.vet.exception.VaccineNotFoundException;
import com.vet.exception.VaccineRegisterNotFoundException;
import com.vet.mappers.VaccineRegisterMapper;
import com.vet.repository.pet.PetRepository;
import com.vet.repository.vaccine.VaccineRepository;
import com.vet.repository.vaccineRegister.VaccineRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service related with pet's vaccines operations
 */
@Service
public class VaccineRegisterService {
    private final VaccineRegisterRepository vaccineRegisterRepository;
    private final PetRepository petRepository;
    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineRegisterService(
            VaccineRegisterRepository vaccineRegisterRepository,
            PetRepository petRepository,
            VaccineRepository vaccineRepository
    ) {
        this.vaccineRegisterRepository = vaccineRegisterRepository;
        this.petRepository = petRepository;
        this.vaccineRepository = vaccineRepository;
    }

    /**
     * Retrieves a page with all vaccines register of the given pet
     *
     * @param page  Page information
     * @param petId Pet ID
     * @return A vaccine register page
     */
    public Page<GetVaccineRegisterDto> getAllVaccinesRegisterByPetId(Pageable page, Long petId) {
        Optional<Pet> retrievedPet = petRepository.findById(petId);
        if (retrievedPet.isEmpty()) {
            throw new PetNotFoundException();
        }
        Page<VaccineRegister> petVaccinesRegisterPage = vaccineRegisterRepository.findByPet(retrievedPet.get(), page);
        return petVaccinesRegisterPage.map(VaccineRegisterMapper::mapEntityToGetVaccineRegisterDto);
    }

    /**
     * Deletes a vaccine register
     *
     * @param id Vaccine register ID to be deleted
     */
    public void deleteVaccineRegister(Long id) {
        Optional<VaccineRegister> retrievedVaccineRegister = vaccineRegisterRepository.findById(id);
        if (retrievedVaccineRegister.isEmpty()) {
            throw new VaccineRegisterNotFoundException();
        }
        VaccineRegister vaccineRegisterToDelete = retrievedVaccineRegister.get();
        vaccineRegisterRepository.delete(vaccineRegisterToDelete);
    }

    /**
     * Creates a vaccine register
     *
     * @param id                       Pet id
     * @param createVaccineRegisterDto DTO that contains the information to create a new vaccine register
     * @return A response with the information of the created vaccine register
     */
    public ResponseEntity<GetVaccineRegisterDto> createVaccineRegister(
            Long id,
            CreateVaccineRegisterDto createVaccineRegisterDto
    ) {
        Optional<Pet> retrievedPet = petRepository.findById(id);
        if (retrievedPet.isEmpty()) {
            throw new PetNotFoundException();
        }
        Optional<Vaccine> retrievedVaccine = vaccineRepository.findById(createVaccineRegisterDto.getVaccine().getId());
        if (retrievedVaccine.isEmpty()) {
            throw new VaccineNotFoundException();
        }

        VaccineRegister vaccineRegisterToSave = VaccineRegisterMapper.mapCreateVaccineRegisterDtoToEntity(
                retrievedPet.get(),
                retrievedVaccine.get(),
                createVaccineRegisterDto.getYear()
        );
        VaccineRegister savedVaccineRegister = vaccineRegisterRepository.save(vaccineRegisterToSave);
        GetVaccineRegisterDto savedVaccineRegisterDto = VaccineRegisterMapper
                .mapEntityToGetVaccineRegisterDto(savedVaccineRegister);

        return new ResponseEntity<>(
                savedVaccineRegisterDto,
                HttpStatus.CREATED
        );
    }
}
