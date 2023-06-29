package com.example.vehicles.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicle;
    private int vehicleYear;
    private String description;
    private boolean sold;
    private String color;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdDate;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updatedDate;

    private String brand;


    @PrePersist
    public void prePersist(){
        createdDate = Instant.now();
    }
    @PreUpdate
    public void preUpdate(){
        updatedDate = Instant.now();
    }
}

