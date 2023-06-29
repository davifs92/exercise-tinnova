package com.example.vehicles.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    private Long id;

    private String vehicle;
    private int vehicleYear;
    private String description;
    private boolean sold;

    private String color;
    private Instant createdDate;

    private Instant updatedDate;
    private String brand;
}
