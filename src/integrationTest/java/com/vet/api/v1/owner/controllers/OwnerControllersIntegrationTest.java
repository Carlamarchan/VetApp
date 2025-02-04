package com.vet.api.v1.owner.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.api.v1.owner.dtos.CreatePetDto;
import com.vet.api.v1.owner.dtos.UpdateOwnerDto;
import com.vet.entities.Owner;
import com.vet.repository.owner.OwnerRepository;
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
public class OwnerControllersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "/api/v1/owners";

    @AfterEach
    public void cleanup() {
        ownerRepository.deleteAll();
    }

    @Test
    public void saveOwnerTest() throws Exception {
        CreatePetDto newCreateOwnerDto = new CreatePetDto(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        mockMvc.perform(post(baseUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newCreateOwnerDto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("name").value("Carla"))
                .andExpect(jsonPath("lastName").value("Marchán"))
                .andExpect(jsonPath("dni").value("1111A"))
                .andExpect(jsonPath("phone").value("123456"));
    }

    @Test
    public void getAllOwnersTest() throws Exception {
        Owner owner1 = new Owner(
                "Carla",
                "Marchán",
                "1111A",
                "123456"
        );

        Owner owner2 = new Owner(
                "Luis",
                "Pérez",
                "2222B",
                "123456"
        );

        List<Owner> ownerList = new ArrayList<>();
        ownerList.add(owner1);
        ownerList.add(owner2);

        ownerRepository.saveAll(ownerList);

        mockMvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("totalElements").value(ownerList.size()));
    }

    @Test
    public void getOwnerByIdTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        mockMvc.perform(get(baseUrl + "/{id}", owner.getId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("lastName").value("Marchán"))
                .andExpect(jsonPath("dni").value("1111A"))
                .andExpect(jsonPath("phone").value("123456"))
                .andExpect(jsonPath("name").value("Carla"));
    }

    @Test
    public void updateOwnerTest() throws Exception {
        Owner owner = new Owner(
                "Marchán",
                "1111A",
                "123456",
                "Carla"
        );

        owner = ownerRepository.save(owner);

        UpdateOwnerDto requestOwner = new UpdateOwnerDto(
                "Laura",
                "Urquizo",
                "2222B",
                "121212"
        );

        mockMvc.perform(put(baseUrl + "/{id}", owner.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestOwner)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id").exists())
                .andExpect(jsonPath("lastName").value("Urquizo"))
                .andExpect(jsonPath("dni").value("2222B"))
                .andExpect(jsonPath("phone").value("121212"))
                .andExpect(jsonPath("name").value("Laura"));
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

        mockMvc.perform(delete(baseUrl + "/{id}", owner.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
