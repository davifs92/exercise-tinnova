package com.example.vehicles.repositories;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.utils.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class VehicleRepositoryTest {

    @Autowired
    private VehicleRepository vehicleRepository;

    private static Factory factory;

    @Test
    void shoudSave_WhenValidObjectIsProvided(){

       Vehicle vehicleToBeSaved = factory.createVehicleEntity();
       Vehicle saved =  vehicleRepository.save(vehicleToBeSaved);

       Assertions.assertNotNull(saved.getId());
       Assertions.assertNotNull(saved.getCreatedDate());
    }

    @Test
    void shouldFindById_WhenIdExists(){
        Vehicle vehicleToBeSaved = factory.createVehicleEntity();
        Vehicle saved =  vehicleRepository.save(vehicleToBeSaved);
        Optional<Vehicle> vehicleFound = vehicleRepository.findById(saved.getId());

        Assertions.assertTrue(vehicleFound.isPresent());
        Assertions.assertEquals(vehicleFound.get().getId(), saved.getId());

    }

    @Test
    void shouldUpdate_WhenObjectExists(){
        Vehicle vehicleToBeSaved = factory.createVehicleEntity();
        Vehicle saved =  vehicleRepository.save(vehicleToBeSaved);
        saved.setBrand("Ford");
        saved.setColor("azul");
        vehicleRepository.save(saved);

        Vehicle vehicleUpdated = vehicleRepository.findById(saved.getId()).get();

        Assertions.assertEquals(vehicleUpdated.getBrand(), "Ford");
        Assertions.assertEquals(vehicleUpdated.getColor(), "azul");


    }

    @Test
    void shoudDelete_WhenIdExists(){
        Vehicle vehicleToBeSaved = factory.createVehicleEntity();
        Vehicle saved =  vehicleRepository.save(vehicleToBeSaved);
        vehicleRepository.deleteById(saved.getId());

        Assertions.assertFalse(vehicleRepository.findById(1L).isPresent());

    }


}
