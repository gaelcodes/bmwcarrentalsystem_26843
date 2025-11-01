package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.enums.AdminRole;
import com.bmwCarRentalSystem.backend.model.Admin;
import com.bmwCarRentalSystem.backend.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
    
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }
    
    public Admin getAdminByEmail(String email) {
        return adminRepository.findByAdminEmail(email);
    }
    
    public List<Admin> getAdminsByRole(AdminRole role) {
        return adminRepository.findByAdminRole(role);
    }
    
    public Page<Admin> getAdminsWithPagination(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }
    
    
    public Admin updateAdmin(Long id, Admin adminDetails) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            Admin existing = admin.get();
            existing.setAdminName(adminDetails.getAdminName());
            existing.setAdminEmail(adminDetails.getAdminEmail());
            existing.setAdminPassword(adminDetails.getAdminPassword());
            existing.setAdminRole(adminDetails.getAdminRole());
            return adminRepository.save(existing);
        }
        return null;
    }
    
    public boolean deleteAdmin(Long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
