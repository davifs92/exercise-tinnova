package com.example.vehicles.utils;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;

public class Factory {

    public static VehicleDTO createVehicleDto(){
        VehicleDTO dto = new VehicleDTO(null, "Siena", 2010,
                "Siena prata", false, "prata", null, null, "Fiat");
        return dto;

            }


    public static Vehicle createVehicleEntity(){
        Vehicle entity = new Vehicle(null, "Polo", 2005,
                "Polo azul", false, "azul", null, null, "Volkswagen");

        return entity;

    }


}
