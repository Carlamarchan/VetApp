package com.vet.mappers;

import com.vet.api.v1.pet.vaccine.dtos.GetVaccineRegisterDto;
import com.vet.entities.VaccineRegister;

public class VaccineRegisterMapper {
    /**
     * Maps an entity to a GetVaccineRegisterDto
     *
     * @param entity A vaccine register entity
     * @return A mapped GetVaccineRegisterDto
     */
    public static GetVaccineRegisterDto mapEntityToGetVaccineRegisterDto(VaccineRegister entity) {
        return new GetVaccineRegisterDto(
                entity.getId(),
                VaccineMapper.mapEntityToGetVaccineDto(entity.getVaccine()),
                entity.getVaccineYear()
        );
    }
}
