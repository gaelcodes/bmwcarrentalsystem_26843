package com.bmwCarRentalSystem.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.bmwCarRentalSystem.backend.enums.District;
import com.bmwCarRentalSystem.backend.enums.Province;
import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    

    public Optional<Customer> getCustomerByName(String name) {
        return customerRepository.findById(name);
    }
    

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByCustEmail(email);
    }
    

    public List<Customer> getCustomersByProvince(Province province) {
        return customerRepository.findByLocation_Province(province);
    }
    

    public List<Customer> getCustomersByDistrict(District district) {
        return customerRepository.findByLocation_District(district);
    }
    

    public Page<Customer> getCustomersWithPagination(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }
    

    public Customer updateCustomer(String name, Customer customerDetails) {
        Optional<Customer> customer = customerRepository.findById(name);
        if (customer.isPresent()) {
            Customer existing = customer.get();
            existing.setCustAddr(customerDetails.getCustAddr());
            existing.setCustPhone(customerDetails.getCustPhone());
            existing.setCustEmail(customerDetails.getCustEmail());
            existing.setCustDriverLicense(customerDetails.getCustDriverLicense());
            existing.setCustPassword(customerDetails.getCustPassword());
            existing.setLocation(customerDetails.getLocation());
            return customerRepository.save(existing);
        }
        return null;
    }
    

    public boolean deleteCustomer(String name) {
        if (customerRepository.existsById(name)) {
            customerRepository.deleteById(name);
            return true;
        }
        return false;
    }
    

    public boolean emailExists(String email) {
        return customerRepository.existsByCustEmail(email);
    }
}
