package com.vet.services.vaccine;

import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.entities.Vaccine;
import com.vet.exception.VaccineNotFoundException;
import com.vet.mappers.VaccineMapper;
import com.vet.repository.vaccine.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
