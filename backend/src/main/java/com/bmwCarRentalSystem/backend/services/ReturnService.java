package com.bmwCarRentalSystem.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.bmwCarRentalSystem.backend.model.Rent;
import com.bmwCarRentalSystem.backend.model.Return;
import com.bmwCarRentalSystem.backend.repository.ReturnRepository;

@Service
public class ReturnService {
    @Autowired
    private ReturnRepository returnRepository;
    

    public Return createReturn(Return returnObj) {
        return returnRepository.save(returnObj);
    }
    

    public List<Return> getAllReturns() {
        return returnRepository.findAll();
    }
    

    public Optional<Return> getReturnById(Long id) {
        return returnRepository.findById(id);
    }
    

    public Return getReturnByRent(Rent rent) {
        return returnRepository.findByRent(rent);
    }
    

    public List<Return> getReturnsWithDelay(Integer minDelay) {
        return returnRepository.findByDelayGreaterThan(minDelay);
    }
    

    public Page<Return> getReturnsWithPagination(Pageable pageable) {
        return returnRepository.findAll(pageable);
    }
    

    public Return updateReturn(Long id, Return returnDetails) {
        Optional<Return> returnObj = returnRepository.findById(id);
        if (returnObj.isPresent()) {
            Return existing = returnObj.get();
            existing.setRent(returnDetails.getRent());
            existing.setReturnDate(returnDetails.getReturnDate());
            existing.setDelay(returnDetails.getDelay());
            existing.setFine(returnDetails.getFine());
            return returnRepository.save(existing);
        }
        return null;
    }
    

    public boolean deleteReturn(Long id) {
        if (returnRepository.existsById(id)) {
            returnRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
