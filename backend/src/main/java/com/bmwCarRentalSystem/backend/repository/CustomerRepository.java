package com.bmwCarRentalSystem.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmwCarRentalSystem.backend.enums.District;
import com.bmwCarRentalSystem.backend.enums.Province;
import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.model.Location;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByCustEmail(String email);
    
    List<Customer> findByLocation(Location location);
    
    List<Customer> findByLocation_Province(Province province);
    
    List<Customer> findByLocation_District(District district);
    
    boolean existsByCustEmail(String email);
    
    Page<Customer> findAll(Pageable pageable);
    
    Customer findByCustPhone(String phone);
}
