package com.example.vehicles.controllers;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repositories.VehicleRepository;
import com.example.vehicles.services.VehicleService;
import com.example.vehicles.utils.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class VehicleControllerIntegration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private ModelMapper modelMapper;
    private final Long vehicleId = 1L;


    @Test
    public void getVehicleById_ShouldReturnVehicle() throws Exception {
        VehicleDTO vehicleDTO = Factory.createVehicleDto();
        Vehicle entity = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(entity);

        mockMvc.perform(get("/veiculos/{id}", vehicleId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(vehicleId.intValue())))
                .andExpect(jsonPath("$.brand", is("Fiat")))
                .andExpect(jsonPath("$.vehicle", is("Siena")));
    }

    @Test
    public void createVehicle_ShouldReturnCreated() throws Exception {
        VehicleDTO vehicleDTO = Factory.createVehicleDto();

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(vehicleId.intValue())))
                .andExpect(jsonPath("$.brand", is("Fiat")))
                .andExpect(jsonPath("$.vehicle", is("Siena")));
    }

    @Test
    public void updateVehicle_ShouldReturnUpdated() throws Exception {
        VehicleDTO vehicleDTO = Factory.createVehicleDto();
        Vehicle entity = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(entity);

        vehicleDTO.setBrand("Toyota");
        vehicleDTO.setVehicle("Camry");

        mockMvc.perform(put("/veiculos/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand", is("Toyota")))
                .andExpect(jsonPath("$.vehicle", is("Camry")));
    }

    @Test
    public void deleteVehicle_ShouldReturnNoContent() throws Exception {

        VehicleDTO vehicleDTO = Factory.createVehicleDto();
        Vehicle entity = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(entity);

        mockMvc.perform(delete("/veiculos/{id}", vehicleId))
                .andExpect(status().isNoContent());
    }

    @Test
    public void patchVehicle_ShouldReturnUpdatedVehicle() throws Exception {
        VehicleDTO vehicleDTO = Factory.createVehicleDto();
        Vehicle entity = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(entity);

        Map<String, Object> updatedFields = new HashMap<>();
        updatedFields.put("brand", "Updated brand");
        updatedFields.put("sold", "true");

        String patchJson = objectMapper.writeValueAsString(updatedFields);

        mockMvc.perform(patch("/veiculos/{id}", vehicleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patchJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(vehicleId.intValue())))
                .andExpect(jsonPath("$.sold", is(true)))
                .andExpect(jsonPath("$.brand", is("Updated brand")));



    }

}

