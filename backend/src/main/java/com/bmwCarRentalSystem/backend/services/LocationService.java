package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.enums.District;
import com.bmwCarRentalSystem.backend.enums.Province;
import com.bmwCarRentalSystem.backend.model.Location;
import com.bmwCarRentalSystem.backend.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
    

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }
    
    
    public List<Location> getLocationsByProvince(Province province) {
        return locationRepository.findByProvince(province);
    }
    

    public List<Location> getLocationsByDistrict(District district) {
        return locationRepository.findByDistrict(district);
    }
    

    public Page<Location> getLocationsWithPagination(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }
    

    public Location updateLocation(Long id, Location locationDetails) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isPresent()) {
            Location existingLocation = location.get();
            existingLocation.setProvince(locationDetails.getProvince());
            existingLocation.setDistrict(locationDetails.getDistrict());
            existingLocation.setSector(locationDetails.getSector());
            existingLocation.setCell(locationDetails.getCell());
            existingLocation.setVillage(locationDetails.getVillage());
            return locationRepository.save(existingLocation);
        }
        return null;
    }
    

    public boolean deleteLocation(Long id) {
        if (locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
            return true;
        }
        return false;
    }
    

    public boolean locationExists(Province province, District district) {
        return locationRepository.existsByProvinceAndDistrict(province, district);
    }
}
