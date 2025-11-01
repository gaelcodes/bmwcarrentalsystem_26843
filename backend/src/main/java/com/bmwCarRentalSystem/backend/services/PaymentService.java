package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.enums.PaymentMethod;
import com.bmwCarRentalSystem.backend.enums.PaymentStatus;
import com.bmwCarRentalSystem.backend.model.Booking;
import com.bmwCarRentalSystem.backend.model.Payment;
import com.bmwCarRentalSystem.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;
    

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }
    

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
    

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }
    

    public Payment getPaymentByBooking(Booking booking) {
        return paymentRepository.findByBooking(booking);
    }
    

    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByPaymentStatus(status);
    }
    

    public List<Payment> getPaymentsByMethod(PaymentMethod method) {
        return paymentRepository.findByPaymentMethod(method);
    }
    

    public Page<Payment> getPaymentsWithPagination(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }
    

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            Payment existing = payment.get();
            existing.setBooking(paymentDetails.getBooking());
            existing.setPaymentDate(paymentDetails.getPaymentDate());
            existing.setPaymentAmount(paymentDetails.getPaymentAmount());
            existing.setPaymentMethod(paymentDetails.getPaymentMethod());
            existing.setPaymentStatus(paymentDetails.getPaymentStatus());
            return paymentRepository.save(existing);
        }
        return null;
    }
    

    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}