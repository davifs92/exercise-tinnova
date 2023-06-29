package com.example.vehicles.controllers;

import com.example.vehicles.controller.VehicleController;
import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.services.VehicleService;
import com.example.vehicles.services.exceptions.ResourceNotFoundException;
import com.example.vehicles.utils.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VehicleController.class)
public class  VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VehicleService vehicleService;

    private Long existingId;
    private Long nonExistingId;
    private Long dependentId;
    private VehicleDTO vehicleDTO;

    @BeforeEach
    void setUp() throws Exception {
        existingId = 1L;
        nonExistingId = 1000L;
        vehicleDTO = Factory.createVehicleDto();

        when(vehicleService.findById(existingId)).thenReturn(vehicleDTO);
        when(vehicleService.findById(nonExistingId)).thenThrow(ResourceNotFoundException.class);
        when(vehicleService.create(vehicleDTO)).thenReturn(vehicleDTO);
        when(vehicleService.update(existingId, vehicleDTO)).thenReturn(vehicleDTO);
        when(vehicleService.findByParameters(ArgumentMatchers.any(),ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(List.of(vehicleDTO));
        when(vehicleService.updateOnlySomeFields(existingId, vehicleDTO)).thenReturn(vehicleDTO);
        doNothing().when(vehicleService).delete(existingId);
        doThrow(ResourceNotFoundException.class).when(vehicleService).delete(nonExistingId);

    }

    @Test
    public void testGetVehicleById() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", existingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicle", is(vehicleDTO.getVehicle())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())));
    }

    @Test
    public void testGetVehicleByIdNonExisting() throws Exception {
        mockMvc.perform(get("/veiculos/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateVehicle() throws Exception {
        String vehicleJson = objectMapper.writeValueAsString(vehicleDTO);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.vehicle", is(vehicleDTO.getVehicle())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())));
    }
    @Test
    public void testDeleteVehicle() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", existingId))
                .andExpect(status().isNoContent());
        verify(vehicleService, times(1)).delete(existingId);
    }

    @Test
    public void testDeleteVehicleNonExisting() throws Exception {
        mockMvc.perform(delete("/veiculos/{id}", nonExistingId))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateVehicle() throws Exception {
        String vehicleJson = objectMapper.writeValueAsString(vehicleDTO);

        mockMvc.perform(put("/veiculos/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicle", is(vehicleDTO.getVehicle())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())));
    }

    @Test
    public void testPartialUpdateVehicle() throws Exception {
        String vehicleJson = objectMapper.writeValueAsString(vehicleDTO);

        mockMvc.perform(patch("/veiculos/{id}", existingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicle", is(vehicleDTO.getVehicle())))
                .andExpect(jsonPath("$.brand", is(vehicleDTO.getBrand())));
    }


}
