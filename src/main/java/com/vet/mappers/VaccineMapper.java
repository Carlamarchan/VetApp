package com.vet.mappers;

import com.vet.api.v1.vaccine.dtos.CreateVaccineDto;
import com.vet.api.v1.vaccine.dtos.GetVaccineDto;
import com.vet.entities.Vaccine;

public class VaccineMapper {
    /**
     * Maps an entity to a GetVaccineDto
     *
     * @param entity A vaccine entity
     * @return A mapped GetVaccineDto
     */
    public static GetVaccineDto mapEntityToGetVaccineDto(Vaccine entity) {
        return new GetVaccineDto(
                entity.getId(),
                entity.getName()
        );
    }

    /**
     * Maps a GetVaccineDto to an entity
     *
     * @param createVaccineDto A GetVaccineDto object
     * @return A mapped entity
     */
    public static Vaccine mapCreateVaccineDtoToEntity(CreateVaccineDto createVaccineDto) {
        return new Vaccine(
                createVaccineDto.getName()
        );
    }
}
