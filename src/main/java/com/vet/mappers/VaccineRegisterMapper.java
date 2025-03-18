package com.vet.mappers;

import com.vet.api.v1.pet.vaccineRegister.dtos.GetVaccineRegisterDto;
import com.vet.entities.Pet;
import com.vet.entities.Vaccine;
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

    /**
     * Maps a CreateVaccineRegisterDto to Entity
     *
     * @param pet         A pet entity
     * @param vaccine     A vaccine entity
     * @param vaccineYear A vaccine year register
     * @return A mapped VaccineRegister entity
     */
    public static VaccineRegister mapCreateVaccineRegisterDtoToEntity(Pet pet, Vaccine vaccine, Integer vaccineYear) {
        return new VaccineRegister(
                pet,
                vaccine,
                vaccineYear
        );
    }
}
