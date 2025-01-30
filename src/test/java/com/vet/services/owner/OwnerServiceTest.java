package com.vet.services.owner;

import com.vet.api.v1.owner.dtos.CreateOwnerDto;
import com.vet.api.v1.owner.dtos.GetOwnerDto;
import com.vet.api.v1.owner.dtos.UpdateOwnerDto;
import com.vet.entities.Owner;
import com.vet.exception.DuplicatedDniException;
import com.vet.exception.OwnerNotFoundException;
import com.vet.repository.owner.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @InjectMocks
    OwnerService service;

    @Mock
    OwnerRepository repository;

    @Test
    public void testGetOwnerByIdExceptions() {
        Long notExistentOwnerId = 10L;

        when(repository.findById(notExistentOwnerId)).thenReturn(Optional.empty());
        assertThrows(
                OwnerNotFoundException.class,
                () -> service.getOwnerById(notExistentOwnerId)
        );
    }

    @Test
    public void testGetOwnerByIdOk() {
        GetOwnerDto expectedOwner = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        ResponseEntity<GetOwnerDto> expectedResponse = new ResponseEntity<>(
                expectedOwner,
                HttpStatus.OK
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(1L);
        existentOwner.setDni("1111A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Marchán");
        existentOwner.setName("Carla");

        Optional<Owner> optionalExistentOwner = Optional.of(existentOwner);
        Long ownerId = 1L;

        when(repository.findById(ownerId)).thenReturn(optionalExistentOwner);
        ResponseEntity<GetOwnerDto> response = service.getOwnerById(ownerId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testCreateOwnerExceptions() {
        CreateOwnerDto existentRequestOwner = new CreateOwnerDto(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(1L);
        existentOwner.setDni("1111A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Marchán");
        existentOwner.setName("Carla");

        Optional<Owner> optionalExistentOwner = Optional.of(existentOwner);

        when(repository.findByDni(existentRequestOwner.getDni())).thenReturn(optionalExistentOwner);
        assertThrows(
                DuplicatedDniException.class,
                () -> service.createOwner(existentRequestOwner)
        );
    }

    @Test
    public void testCreateOwnerOk() {
        CreateOwnerDto requestOwner = new CreateOwnerDto(
                "Juan",
                "Perez",
                "2222B",
                "123456"
        );

        Owner savedOwner = new Owner();
        savedOwner.setId(2L);
        savedOwner.setDni("2222B");
        savedOwner.setPhone("123456");
        savedOwner.setLastName("Perez");
        savedOwner.setName("Juan");

        Optional<Owner> optionalNotExistentOwner = Optional.empty();

        when(repository.findByDni(requestOwner.getDni())).thenReturn(optionalNotExistentOwner);
        when(repository.save(any(Owner.class))).thenReturn(savedOwner);
        ResponseEntity<GetOwnerDto> response = service.createOwner(requestOwner);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(savedOwner.getDni(), response.getBody().getDni());
        assertEquals(savedOwner.getName(), response.getBody().getName());
        assertEquals(savedOwner.getLastName(), response.getBody().getLastName());
        assertEquals(savedOwner.getPhone(), response.getBody().getPhone());
    }

    @Test
    public void testUpdateExceptions() {
        Long notExistentOwnerId = 3L;

        when(repository.findById(notExistentOwnerId)).thenReturn(Optional.empty());
        assertThrows(
                OwnerNotFoundException.class,
                () -> service.updateOwner(notExistentOwnerId, Mockito.mock(UpdateOwnerDto.class))
        );

        Long duplicatedOwnerId = 2L;

        UpdateOwnerDto requestOwner = new UpdateOwnerDto(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        Owner existentOwner1 = new Owner();
        existentOwner1.setId(1L);
        existentOwner1.setDni("1111A");
        existentOwner1.setPhone("123456");
        existentOwner1.setLastName("Pérez");
        existentOwner1.setName("Luis");

        Optional<Owner> optionalExistentOwner1 = Optional.of(existentOwner1);

        Owner existentOwner2 = new Owner();
        existentOwner2.setId(2L);
        existentOwner2.setDni("2222A");
        existentOwner2.setPhone("123456");
        existentOwner2.setLastName("Arias");
        existentOwner2.setName("Juan");

        Optional<Owner> optionalExistentOwner2 = Optional.of(existentOwner2);

        when(repository.findById(duplicatedOwnerId)).thenReturn(optionalExistentOwner2);
        when(repository.findByDni(requestOwner.getDni())).thenReturn(optionalExistentOwner1);

        assertThrows(
                DuplicatedDniException.class,
                () -> service.updateOwner(duplicatedOwnerId, requestOwner)
        );

    }

    @Test
    public void testUpdateOwnerOk() {
        Long ownerId = 2L;

        UpdateOwnerDto requestOwner = new UpdateOwnerDto(
                "Katy",
                "Urquizo",
                "3333A",
                "123456"
        );

        Owner existentOwner = new Owner();
        existentOwner.setId(2L);
        existentOwner.setDni("2222A");
        existentOwner.setPhone("123456");
        existentOwner.setLastName("Arias");
        existentOwner.setName("Juan");

        Optional<Owner> optionalExistentOwner = Optional.of(existentOwner);

        Owner updatedOwner = new Owner();
        updatedOwner.setId(2L);
        updatedOwner.setDni("3333A");
        updatedOwner.setPhone("123456");
        updatedOwner.setLastName("Urquizo");
        updatedOwner.setName("Katy");

        when(repository.findById(ownerId)).thenReturn(optionalExistentOwner);
        when(repository.findByDni(requestOwner.getDni())).thenReturn(Optional.empty());
        when(repository.save(any(Owner.class))).thenReturn(updatedOwner);

        ResponseEntity<GetOwnerDto> response = service.updateOwner(ownerId, requestOwner);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(requestOwner.getDni(), response.getBody().getDni());
        assertEquals(requestOwner.getName(), response.getBody().getName());
        assertEquals(requestOwner.getLastName(), response.getBody().getLastName());
        assertEquals(requestOwner.getPhone(), response.getBody().getPhone());
    }

    @Test
    public void testDeleteOwnerExceptions() {
        Long notExistentOwner = 1L;

        when(repository.findById(notExistentOwner)).thenReturn(Optional.empty());
        assertThrows(
                OwnerNotFoundException.class,
                () -> service.deleteOwner(notExistentOwner)
        );
    }

    @Test
    public void testDeleteOwnerOK() {
        Long ownerId = 2L;

        Owner retrievedOwner = new Owner();
        retrievedOwner.setId(2L);
        retrievedOwner.setDni("1111A");
        retrievedOwner.setPhone("123456");
        retrievedOwner.setLastName("Marchán");
        retrievedOwner.setName("Carla");

        Optional<Owner> optionalRetrievedOwner = Optional.of(retrievedOwner);

        when(repository.findById(ownerId)).thenReturn(optionalRetrievedOwner);
        service.deleteOwner(ownerId);
        verify(repository, times(1)).delete(retrievedOwner);
    }

    @Test
    public void testGetAllOwners() {
        GetOwnerDto expectedOwner1 = new GetOwnerDto(
                1L,
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("Carla");
        owner1.setLastName("Marchán");
        owner1.setDni("1111A");
        owner1.setPhone("123456");

        GetOwnerDto expectedOwner2 = new GetOwnerDto(
                2L,
                "Luis",
                "Pérez",
                "2222B",
                "123456"
        );

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Luis");
        owner2.setLastName("Pérez");
        owner2.setDni("2222B");
        owner2.setPhone("123456");

        GetOwnerDto expectedOwner3 = new GetOwnerDto(
                3L,
                "Daniela",
                "Arias",
                "3333C",
                "123456"
        );

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setName("Daniela");
        owner3.setLastName("Arias");
        owner3.setDni("3333C");
        owner3.setPhone("123456");

        Pageable pageOwner = PageRequest.of(0, 3);
        List<Owner> ownerList = List.of(owner1, owner2, owner3);
        Page<Owner> resultOwner = new PageImpl<>(ownerList, pageOwner, 1);

        when(repository.findAll(pageOwner)).thenReturn(resultOwner);
        Page<GetOwnerDto> pageResponse = service.getAllOwners(pageOwner);
        assertEquals(3L, pageResponse.getTotalElements());
        assertEquals(expectedOwner1, pageResponse.getContent().get(0));
        assertEquals(expectedOwner2, pageResponse.getContent().get(1));
        assertEquals(expectedOwner3, pageResponse.getContent().get(2));
    }
}