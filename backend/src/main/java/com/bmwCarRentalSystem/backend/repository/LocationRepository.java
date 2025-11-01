package com.bmwCarRentalSystem.backend.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmwCarRentalSystem.backend.enums.District;
import com.bmwCarRentalSystem.backend.enums.Province;
import com.bmwCarRentalSystem.backend.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findByProvince(Province province);
    
    List<Location> findByDistrict(District district);
    
    boolean existsByProvinceAndDistrict(Province province, District district);
    
    Page<Location> findAll(Pageable pageable);
}
