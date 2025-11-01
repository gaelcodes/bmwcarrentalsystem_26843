package com.bmwCarRentalSystem.backend.model;

import java.sql.Timestamp;

import com.bmwCarRentalSystem.backend.enums.AdminRole;

import jakarta.persistence.*;


@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;
    
    private String adminName;
    
    @Column(unique = true)
    private String adminEmail;
    
    private String adminPassword;
    
    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;
    
    public Admin() {}
    
    public Long getAdminId() { return adminId; }
    public void setAdminId(Long adminId) { this.adminId = adminId; }
    
    public String getAdminName() { return adminName; }
    public void setAdminName(String adminName) { this.adminName = adminName; }
    
    public String getAdminEmail() { return adminEmail; }
    public void setAdminEmail(String adminEmail) { this.adminEmail = adminEmail; }
    
    public String getAdminPassword() { return adminPassword; }
    public void setAdminPassword(String adminPassword) { this.adminPassword = adminPassword; }
    
    public AdminRole getAdminRole() { return adminRole; }
    public void setAdminRole(AdminRole adminRole) { this.adminRole = adminRole; }
}