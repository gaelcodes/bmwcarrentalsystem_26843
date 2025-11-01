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

import com.bmwCarRentalSystem.backend.enums.PaymentMethod;
import com.bmwCarRentalSystem.backend.enums.PaymentStatus;
import com.bmwCarRentalSystem.backend.model.Booking;
import com.bmwCarRentalSystem.backend.model.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByBooking(Booking booking);
    
    List<Payment> findByPaymentStatus(PaymentStatus status);
    
    List<Payment> findByPaymentMethod(PaymentMethod method);
    
    List<Payment> findByPaymentDateBetween(LocalDate startDate, LocalDate endDate);
    
    boolean existsByBooking(Booking booking);
    
    Page<Payment> findAll(Pageable pageable);
}
