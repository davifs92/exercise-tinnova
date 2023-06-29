package com.example.vehicles.controller;

import com.example.vehicles.dto.VehicleDTO;
import com.example.vehicles.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/veiculos")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping(value = "/")
    public ResponseEntity<List<VehicleDTO>> findByParameters(@RequestParam(required = false) String marca,
                                                    @RequestParam(required = false) Integer year,
                                                    @RequestParam(required = false) String color) {
        List<VehicleDTO> dto = vehicleService.findByParameters(marca, year, color);
        return ResponseEntity.ok().body(dto);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        VehicleDTO dto = vehicleService.findById(id);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping
    public ResponseEntity<VehicleDTO> create(@RequestBody VehicleDTO dto){
        VehicleDTO savedDto = vehicleService.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(savedDto.getId()).toUri();
        return ResponseEntity.created(uri).body(savedDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> update(@PathVariable Long id, @RequestBody VehicleDTO dto){
        VehicleDTO savedDto = vehicleService.update(id, dto);
        return ResponseEntity.ok().body(savedDto);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> updateOnlySomeFields(@PathVariable Long id, @RequestBody VehicleDTO dto){
        VehicleDTO savedDto = vehicleService.updateOnlySomeFields(id, dto);
        return ResponseEntity.ok().body(savedDto);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<VehicleDTO> delete(@PathVariable Long id){
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
