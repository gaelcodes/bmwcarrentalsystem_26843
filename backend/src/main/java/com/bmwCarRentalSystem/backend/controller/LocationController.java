package com.bmwCarRentalSystem.backend.controller;

import com.bmwCarRentalSystem.backend.enums.District;
import com.bmwCarRentalSystem.backend.enums.Province;
import com.bmwCarRentalSystem.backend.model.*;
import com.bmwCarRentalSystem.backend.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    
    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location newLocation = locationService.createLocation(location);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location> locations = locationService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        Optional<Location> location = locationService.getLocationById(id);
        return location.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/province/{province}")
    public ResponseEntity<List<Location>> getLocationsByProvince(@PathVariable Province province) {
        List<Location> locations = locationService.getLocationsByProvince(province);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @GetMapping("/district/{district}")
    public ResponseEntity<List<Location>> getLocationsByDistrict(@PathVariable District district) {
        List<Location> locations = locationService.getLocationsByDistrict(district);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @GetMapping("/paginated")
    public ResponseEntity<Page<Location>> getLocationsWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Location> locations = locationService.getLocationsWithPagination(pageable);
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location updatedLocation = locationService.updateLocation(id, location);
        if (updatedLocation != null) {
            return new ResponseEntity<>(updatedLocation, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        boolean deleted = locationService.deleteLocation(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
