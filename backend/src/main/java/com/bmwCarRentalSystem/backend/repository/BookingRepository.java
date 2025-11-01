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

import com.bmwCarRentalSystem.backend.enums.BookingStatus;
import com.bmwCarRentalSystem.backend.model.Booking;
import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.model.ManageCar;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomer(Customer customer);
    
    List<Booking> findByCar(ManageCar car);
    
    List<Booking> findByStatus(BookingStatus status);
    
    List<Booking> findByCustomerAndStatus(Customer customer, BookingStatus status);
    
    boolean existsByCarAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        ManageCar car, LocalDate endDate, LocalDate startDate);
    
    Page<Booking> findAll(Pageable pageable);
    
    Page<Booking> findByCustomer(Customer customer, Pageable pageable);
}
