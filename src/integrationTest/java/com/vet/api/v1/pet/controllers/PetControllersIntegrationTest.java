package com.vet.api.v1.pet.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.api.v1.owner.dtos.OwnerIdDto;
import com.vet.api.v1.pet.dtos.CreatePetDto;
import com.vet.api.v1.pet.dtos.UpdatePetDto;
import com.vet.entities.Owner;
import com.vet.entities.Pet;
import com.vet.enums.PetType;
import com.vet.repository.owner.OwnerRepository;
import com.vet.repository.pet.PetRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PetControllersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "/api/v1/pets";

    @AfterEach
    public void cleanupPets() {
        petRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    @Test
    public void createPetTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        OwnerIdDto ownerId = new OwnerIdDto(
                owner.getId()
        );

        CreatePetDto requestPet = new CreatePetDto(
                "Bruno",
                "1111A",
                PetType.DOG,
                ownerId
        );

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPet)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Bruno"))
                .andExpect(jsonPath("chipNumber").value("1111A"))
                .andExpect(jsonPath("type").value(String.valueOf(PetType.DOG)))
                .andExpect(jsonPath("owner.id").exists())
                .andExpect(jsonPath("owner.name").value("Carla"))
                .andExpect(jsonPath("owner.lastName").value("Marchán"))
                .andExpect(jsonPath("owner.dni").value("1111A"))
                .andExpect(jsonPath("owner.phone").value("123456"));
    }

    @Test
    public void getAllPetsTest() throws Exception {
        Owner owner1 = new Owner(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );
        owner1 = ownerRepository.save(owner1);

        Owner owner2 = new Owner(
                "Luis",
                "Pérez",
                "2222B",
                "123456"
        );
        owner2 = ownerRepository.save(owner2);

        Pet pet1 = new Pet(
                "Bruno",
                "1111A",
                PetType.DOG,
                owner1
        );

        Pet pet2 = new Pet(
                "Mordelón",
                "2222B",
                PetType.CAT,
                owner2
        );

        List<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);

        petRepository.saveAll(petList);

        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("totalElements").value(petList.size()));
    }

    @Test
    public void getPetTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        Pet pet = new Pet(
                "Bruno",
                "1111A",
                PetType.DOG,
                owner
        );

        pet = petRepository.save(pet);

        mockMvc.perform(get(baseUrl + "/{id}", pet.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Bruno"))
                .andExpect(jsonPath("chipNumber").value("1111A"))
                .andExpect(jsonPath("type").value(String.valueOf(PetType.DOG)))
                .andExpect(jsonPath("owner.id").exists())
                .andExpect(jsonPath("owner.name").value("Carla"))
                .andExpect(jsonPath("owner.lastName").value("Marchán"))
                .andExpect(jsonPath("owner.dni").value("1111A"))
                .andExpect(jsonPath("owner.phone").value("123456"));
    }

    @Test
    public void updatePetTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        Pet pet = new Pet(
                "Bruno",
                "1111A",
                PetType.DOG,
                owner
        );

        pet = petRepository.save(pet);

        UpdatePetDto requestPet = new UpdatePetDto(
                "Mordelón",
                "2222B",
                PetType.CAT
        );

        mockMvc.perform(put(baseUrl + "/{id}", pet.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestPet)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Mordelón"))
                .andExpect(jsonPath("chipNumber").value("2222B"))
                .andExpect(jsonPath("type").value(String.valueOf(PetType.CAT)))
                .andExpect(jsonPath("owner.id").exists())
                .andExpect(jsonPath("owner.name").value("Carla"))
                .andExpect(jsonPath("owner.lastName").value("Marchán"))
                .andExpect(jsonPath("owner.dni").value("1111A"))
                .andExpect(jsonPath("owner.phone").value("123456"));
    }


    @Test
    public void changePetOwnerTest() throws Exception {
        Owner previousOwner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        previousOwner = ownerRepository.save(previousOwner);

        Pet pet = new Pet(
                "Bruno",
                "1111A",
                PetType.DOG,
                previousOwner
        );

        pet = petRepository.save(pet);

        Owner newOwner = new Owner(
                "Urquizo",
                "4444A",
                "123456",
                "Luisa"
        );

        newOwner = ownerRepository.save(newOwner);

        mockMvc.perform(put(baseUrl + "/{id}/change_owner", pet.getId())
                        .param("ownerId", newOwner.getId().toString()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Bruno"))
                .andExpect(jsonPath("chipNumber").value("1111A"))
                .andExpect(jsonPath("type").value(String.valueOf(PetType.DOG)))
                .andExpect(jsonPath("owner.id").exists())
                .andExpect(jsonPath("owner.name").value("Luisa"))
                .andExpect(jsonPath("owner.lastName").value("Urquizo"))
                .andExpect(jsonPath("owner.dni").value("4444A"))
                .andExpect(jsonPath("owner.phone").value("123456"));
    }

    @Test
    public void deleteOwnerTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        Pet pet = new Pet(
                "Bruno",
                "1111A",
                PetType.DOG,
                owner
        );

        pet = petRepository.save(pet);

        mockMvc.perform(delete("/api/v1/pets/{id}", pet.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}

