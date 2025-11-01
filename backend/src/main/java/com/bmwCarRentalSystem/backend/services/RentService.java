package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.model.ManageCar;
import com.bmwCarRentalSystem.backend.model.Rent;
import com.bmwCarRentalSystem.backend.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;
    

    public Rent createRent(Rent rent) {
        return rentRepository.save(rent);
    }
    

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
    

    public Optional<Rent> getRentById(Long id) {
        return rentRepository.findById(id);
    }
    

    public List<Rent> getRentsByCustomer(Customer customer) {
        return rentRepository.findByCustomer(customer);
    }
    

    public List<Rent> getRentsByCar(ManageCar car) {
        return rentRepository.findByCar(car);
    }
    

    public List<Rent> getActiveRents(LocalDate currentDate) {
        return rentRepository.findByReturnDateAfter(currentDate);
    }
    

    public Page<Rent> getRentsWithPagination(Pageable pageable) {
        return rentRepository.findAll(pageable);
    }
    

    public Rent updateRent(Long id, Rent rentDetails) {
        Optional<Rent> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            Rent existing = rent.get();
            existing.setCar(rentDetails.getCar());
            existing.setCustomer(rentDetails.getCustomer());
            existing.setRentDate(rentDetails.getRentDate());
            existing.setReturnDate(rentDetails.getReturnDate());
            existing.setRentFee(rentDetails.getRentFee());
            return rentRepository.save(existing);
        }
        return null;
    }
    

    public boolean deleteRent(Long id) {
        if (rentRepository.existsById(id)) {
            rentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
