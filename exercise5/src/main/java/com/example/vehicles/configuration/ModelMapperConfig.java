package com.example.vehicles.configuration;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.beans.factory.support.InstanceSupplier.using;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        Converter<Integer, Integer> zeroConverter =
                source -> source.getSource() != null && source.getSource() != 0 ? source.getSource() : source.getDestination();


        modelMapper.addMappings(new PropertyMap<VehicleDTO, Vehicle>() {
            protected void configure() {
                skip().setId(null);
                skip().setCreatedDate(null);
                skip().setUpdatedDate(null);
                using(zeroConverter).map(source.getVehicleYear(), destination.getVehicleYear());
            }
        });

        return modelMapper;
    }

}
