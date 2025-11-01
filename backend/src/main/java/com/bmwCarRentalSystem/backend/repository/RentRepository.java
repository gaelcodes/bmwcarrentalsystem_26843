package com.bmwCarRentalSystem.backend.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.model.ManageCar;
import com.bmwCarRentalSystem.backend.model.Rent;


@Repository
public interface RentRepository extends JpaRepository<Rent, Long> {

    List<Rent> findByCustomer(Customer customer);
    
    List<Rent> findByCar(ManageCar car);
    
    List<Rent> findByRentDateBetween(LocalDate startDate, LocalDate endDate);
    
    List<Rent> findByReturnDateAfter(LocalDate currentDate);
    
    boolean existsByCar(ManageCar car);
    
    Page<Rent> findAll(Pageable pageable);
}
