package com.example.vehicles.repositories;

import com.example.vehicles.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
   @Query("SELECT obj FROM Vehicle obj WHERE (:brand IS NULL OR obj.brand = :brand) AND " +
           "(:vehicleYear IS NULL OR obj.vehicleYear = :vehicleYear) AND " +
           "(:color IS NULL OR obj.color = :color)")
   List<Vehicle> findByParameters(@Param("brand") String brand, @Param("vehicleYear") Integer vehicleYear, @Param("color") String color);
}


