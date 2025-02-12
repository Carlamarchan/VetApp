package com.vet.services.vaccine;

import com.vet.api.v1.vaccine.dtos.CreateVaccineDto;
import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.api.v1.vaccine.dtos.UpdateVaccineDto;
import com.vet.entities.Vaccine;
import com.vet.exception.DuplicatedVaccineNameException;
import com.vet.exception.VaccineNotFoundException;
import com.vet.mappers.VaccineMapper;
import com.vet.repository.vaccine.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * Service related with vaccine's operations
 */
@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;

    @Autowired
    public VaccineService(VaccineRepository vaccineRepository) {
        this.vaccineRepository = vaccineRepository;
    }

    /**
     * Retrieves a vaccine by a given id
     *
     * @param id Vaccine id
     * @return A response for an existent vaccine, otherwise a 404 error is returned
     */
    public ResponseEntity<GetVaccineDto> getVaccineById(Long id) {
        Optional<Vaccine> optionalVaccine = vaccineRepository.findById(id);
        if (optionalVaccine.isEmpty()) {
            throw new VaccineNotFoundException();
        }
        return new ResponseEntity<>(
                VaccineMapper.mapEntityToGetVaccineDto(optionalVaccine.get()),
                HttpStatus.OK
        );
    }

    /**
     * Creates a vaccine
     *
     * @param vaccineDto DTO that contains the information needed to create a vaccine
     * @return A response with the information of the created vaccine
     */
    public ResponseEntity<GetVaccineDto> createVaccine(CreateVaccineDto vaccineDto) {
        Optional<Vaccine> retrievedVaccine = vaccineRepository.findByName(vaccineDto.getName());
        if (retrievedVaccine.isPresent()) {
            throw new DuplicatedVaccineNameException();
        }

        Vaccine vaccineToSave = VaccineMapper.mapCreateVaccineDtoToEntity(vaccineDto);
        Vaccine savedVaccine = vaccineRepository.save(vaccineToSave);
        GetVaccineDto savedVaccineDto = VaccineMapper.mapEntityToGetVaccineDto(savedVaccine);

        return new ResponseEntity<>(
                savedVaccineDto,
                HttpStatus.CREATED
        );
    }

    /**
     * Updates a vaccine
     *
     * @param id         Vaccine Id
     * @param vaccineDto DTO that contains the information needed to update a vaccine
     * @return A response with the information of the updated vaccine
     */
    public ResponseEntity<GetVaccineDto> updateVaccine(Long id, UpdateVaccineDto vaccineDto) {
        Optional<Vaccine> retrievedVaccineById = vaccineRepository.findById(id);
        if (retrievedVaccineById.isEmpty()) {
            throw new VaccineNotFoundException();
        }
        Optional<Vaccine> retrievedVaccineByName = vaccineRepository.findByName(vaccineDto.getName());
        if ((retrievedVaccineByName.isPresent()) && !Objects.equals(id, retrievedVaccineByName.get().getId())) {
            throw new DuplicatedVaccineNameException();
        }
        Vaccine vaccineToUpdate = retrievedVaccineById.get();
        vaccineToUpdate.setName(vaccineDto.getName());

        Vaccine updatedVaccine = vaccineRepository.save(vaccineToUpdate);
        GetVaccineDto updatedVaccineDto = VaccineMapper.mapEntityToGetVaccineDto(updatedVaccine);
        return new ResponseEntity<>(
                updatedVaccineDto,
                HttpStatus.CREATED
        );
    }

    /**
     * Deletes a vaccine
     *
     * @param id Vaccine ID
     */
    public void deleteVaccine(Long id) {
        Optional<Vaccine> retrievedVaccine = vaccineRepository.findById(id);
        if (retrievedVaccine.isEmpty()) {
            throw new VaccineNotFoundException();
        }
        Vaccine vaccineToDelete = retrievedVaccine.get();
        vaccineRepository.delete(vaccineToDelete);
    }

    /**
     *Retrieves a page with vaccines
     *
     * @param page Vaccine Information
     * @return A vaccines page
     */
    public Page<GetVaccineDto> getAllVaccines(Pageable page) {
        Page<Vaccine> vaccinePage = vaccineRepository.findAll(page);
        return vaccinePage.map(VaccineMapper::mapEntityToGetVaccineDto);
    }
}