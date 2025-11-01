package com.bmwCarRentalSystem.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmwCarRentalSystem.backend.enums.AdminRole;
import com.bmwCarRentalSystem.backend.model.Admin;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByAdminEmail(String email);
    
    List<Admin> findByAdminRole(AdminRole role);
    
    boolean existsByAdminEmail(String email);
    
    Page<Admin> findAll(Pageable pageable);
}
