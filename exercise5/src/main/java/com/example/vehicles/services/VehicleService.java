package com.example.vehicles.services;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.entities.Vehicle;
import com.example.vehicles.repositories.VehicleRepository;
import com.example.vehicles.services.exceptions.DataBaseException;
import com.example.vehicles.services.exceptions.ResourceNotFoundException;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Transactional(readOnly = true)
    public VehicleDTO findById(Long id){
     Vehicle vehicleEntity = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        return modelMapper.map(vehicleEntity, VehicleDTO.class);
    }
    @Transactional(readOnly = true)
    public List<VehicleDTO> findByParameters(String brand, Integer vehicleYear, String color){
       return vehicleRepository.findByParameters(brand, vehicleYear, color)
               .stream()
               .map(vehicle -> modelMapper.map(vehicle, VehicleDTO.class))
               .collect(Collectors.toList());
    }

    @Transactional
    public VehicleDTO create(VehicleDTO dto) {
        Vehicle entity = modelMapper.map(dto, Vehicle.class);
        Vehicle saved = vehicleRepository.save(entity);
        return modelMapper.map(saved, VehicleDTO.class);
    }

    @Transactional
    public VehicleDTO updateOnlySomeFields(Long id, VehicleDTO dto){
        Vehicle entity = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(dto, entity);
        Vehicle updated = vehicleRepository.save(entity);
        return modelMapper.map(updated, VehicleDTO.class);
    }

    @Transactional
    public VehicleDTO update(Long id, VehicleDTO dto){
        Vehicle entity = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        modelMapper.map(dto, entity);
        Vehicle updated = vehicleRepository.save(entity);
        return modelMapper.map(updated, VehicleDTO.class);
    }



    public void delete(Long id){
        try {
            vehicleRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException("Id not found");
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }
}
