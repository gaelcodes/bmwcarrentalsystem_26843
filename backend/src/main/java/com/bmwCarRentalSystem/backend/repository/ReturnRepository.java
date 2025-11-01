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

import com.bmwCarRentalSystem.backend.model.Rent;
import com.bmwCarRentalSystem.backend.model.Return;


@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {

    Return findByRent(Rent rent);
    
    List<Return> findByDelayGreaterThan(Integer delay);
    
    List<Return> findByReturnDateBetween(LocalDate startDate, LocalDate endDate);
    
    boolean existsByRent(Rent rent);
    
    Page<Return> findAll(Pageable pageable);
}
