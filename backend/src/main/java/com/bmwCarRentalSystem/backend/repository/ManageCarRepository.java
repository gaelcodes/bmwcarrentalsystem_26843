package com.bmwCarRentalSystem.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmwCarRentalSystem.backend.enums.CarStatus;
import com.bmwCarRentalSystem.backend.enums.CarType;
import com.bmwCarRentalSystem.backend.model.ManageCar;

@Repository
public interface ManageCarRepository extends JpaRepository<ManageCar, String> {

    List<ManageCar> findByCarStatus(CarStatus status);
    
    List<ManageCar> findByCarType(CarType type);
    
    List<ManageCar> findByCarStatusAndCarType(CarStatus status, CarType type);
    
    List<ManageCar> findByCarYearBetween(Integer startYear, Integer endYear);
    
    List<ManageCar> findByCarPriceBetween(Double minPrice, Double maxPrice);
    
    boolean existsByCarPlatenum(String plateNum);
    
    Page<ManageCar> findAll(Pageable pageable);
    
    Page<ManageCar> findByCarStatus(CarStatus status, Pageable pageable);
}
