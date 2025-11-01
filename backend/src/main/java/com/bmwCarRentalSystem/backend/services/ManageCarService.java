package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.enums.CarStatus;
import com.bmwCarRentalSystem.backend.enums.CarType;
import com.bmwCarRentalSystem.backend.model.ManageCar;
import com.bmwCarRentalSystem.backend.repository.ManageCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ManageCarService {
    @Autowired
    private ManageCarRepository manageCarRepository;
    

    public ManageCar createCar(ManageCar car) {
        return manageCarRepository.save(car);
    }
    

    public List<ManageCar> getAllCars() {
        return manageCarRepository.findAll();
    }
    

    public Optional<ManageCar> getCarByPlateNum(String plateNum) {
        return manageCarRepository.findById(plateNum);
    }
    

    public List<ManageCar> getCarsByStatus(CarStatus status) {
        return manageCarRepository.findByCarStatus(status);
    }
    

    public List<ManageCar> getCarsByType(CarType type) {
        return manageCarRepository.findByCarType(type);
    }
    

    public List<ManageCar> getAvailableCarsByType(CarType type) {
        return manageCarRepository.findByCarStatusAndCarType(CarStatus.available, type);
    }
    

    public List<ManageCar> getCarsByPriceRange(Double minPrice, Double maxPrice) {
        return manageCarRepository.findByCarPriceBetween(minPrice, maxPrice);
    }
    

    public Page<ManageCar> getCarsWithPagination(Pageable pageable) {
        return manageCarRepository.findAll(pageable);
    }
    

    public ManageCar updateCar(String plateNum, ManageCar carDetails) {
        Optional<ManageCar> car = manageCarRepository.findById(plateNum);
        if (car.isPresent()) {
            ManageCar existing = car.get();
            existing.setCarType(carDetails.getCarType());
            existing.setCarModel(carDetails.getCarModel());
            existing.setCarYear(carDetails.getCarYear());
            existing.setCarPrice(carDetails.getCarPrice());
            existing.setCarStatus(carDetails.getCarStatus());
            return manageCarRepository.save(existing);
        }
        return null;
    }
    

    public boolean deleteCar(String plateNum) {
        if (manageCarRepository.existsById(plateNum)) {
            manageCarRepository.deleteById(plateNum);
            return true;
        }
        return false;
    }
}
