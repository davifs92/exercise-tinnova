package com.example.vehicles.services;
import com.example.vehicles.configuration.ModelMapperConfig;
import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repositories.VehicleRepository;
import com.example.vehicles.services.exceptions.DataBaseException;
import com.example.vehicles.services.exceptions.ResourceNotFoundException;
import com.example.vehicles.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@Import(ModelMapperConfig.class)
public class VehicleServiceTest {

    @InjectMocks
    private VehicleService vehicleService;
    @Mock
    private VehicleRepository vehicleRepository;
    @Autowired
    public ModelMapper modelMapper;

    @BeforeEach
    void setUp() throws Exception{
        Field modelMapperField = VehicleService.class.getDeclaredField("modelMapper");
        modelMapperField.setAccessible(true);
        modelMapperField.set(vehicleService, modelMapper);

        Vehicle vehicle = Factory.createVehicleEntity();
        Vehicle vehicle2 = Factory.createVehicleEntity();
        List<Vehicle> vehicleList = List.of(vehicle, vehicle2);

        Mockito.doNothing().when(vehicleRepository).deleteById(1L);
        Mockito.doThrow(ResourceNotFoundException.class).when(vehicleRepository).deleteById(1000L);
        Mockito.doThrow(DataBaseException.class).when(vehicleRepository).deleteById(4L);
        Mockito.when(vehicleRepository.findByParameters(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(vehicleList);
        Mockito.when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(vehicle);
        Mockito.when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));
        Mockito.when(vehicleRepository.findById(1000L)).thenReturn(Optional.empty());
    }

      @Test
    public void deleteShouldDoNothingWhenIdExists(){
        Assertions.assertDoesNotThrow(() -> {
            vehicleService.delete(1L);
        });
        Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(1L);

    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            vehicleService.delete(1000L);
        });
        Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(1000L);
    }

    @Test
    public void deleteShouldThrowDataBaseExceptionWhenIdDoesNotExists(){
        Assertions.assertThrows(DataBaseException.class, () -> {
            vehicleService.delete(4L);
        });
        Mockito.verify(vehicleRepository, Mockito.times(1)).deleteById(4L);
    }

    @Test
    public void findByIdShouldReturnProduct() {
        Assertions.assertDoesNotThrow(() ->  vehicleService.findById(1L));
        Mockito.verify(vehicleRepository, Mockito.times(1)).findById(1L);
        VehicleDTO result = vehicleService.findById(1L);
        Assertions.assertNotNull(result);
        Assertions.assertThrows(ResourceNotFoundException.class, () -> vehicleService.findById(1000L));

    }

    @Test
    public void findAllShouldReturnListOfProducts() {
        List<VehicleDTO> result = vehicleService.findByParameters(null, null, null);
        Assertions.assertEquals(2, result.size());
        Mockito.verify(vehicleRepository, Mockito.times(1)).findByParameters(null, null, null);
    }

    @Test
    public void createShouldReturnCreatedProduct() {
        VehicleDTO newVehicle = Factory.createVehicleDto();
        VehicleDTO result = vehicleService.create(newVehicle);
        Assertions.assertNotNull(result);
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(ArgumentMatchers.any(Vehicle.class));
    }

    @Test
    public void updateShouldReturnUpdatedProduct() {
        VehicleDTO existingVehicle = Factory.createVehicleDto();
        existingVehicle.setId(1L);

        VehicleDTO updatedVehicle = Factory.createVehicleDto();
        updatedVehicle.setBrand("Updated Brand");

        Vehicle existingEntity = Factory.createVehicleEntity();

        Mockito.when(vehicleRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
        existingEntity.setBrand("Updated Brand");
        Mockito.when(vehicleRepository.save(ArgumentMatchers.any(Vehicle.class))).thenReturn(existingEntity);

        VehicleDTO result = vehicleService.update(1L, updatedVehicle);

        Assertions.assertEquals("Updated Brand", result.getBrand());
        Mockito.verify(vehicleRepository, Mockito.times(1)).save(existingEntity);
    }




}
