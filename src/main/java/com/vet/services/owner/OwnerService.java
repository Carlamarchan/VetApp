package com.vet.services.owner;

import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.entities.Owner;
import com.vet.repository.owner.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service related of owner's operations
 */
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    /**
     * Retrieves an owner by a given id
     *
     * @param id Owner id
     * @return A response for an existent owner, otherwise a 404 error is returned
     */
    public ResponseEntity<GetOwnerDto> getOwnerById(Long id) {
        Optional<Owner> optionalOwner = ownerRepository.findById(id);
        if (optionalOwner.isEmpty()) {
            return new ResponseEntity<>(
                    HttpStatus.NOT_FOUND
            );
        }

        return new ResponseEntity<>(
                OwnerMapper.mapEntityToGetOwnerDto(optionalOwner.get()),
                HttpStatus.OK
        );
    }
}
