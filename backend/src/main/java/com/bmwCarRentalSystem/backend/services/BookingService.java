package com.bmwCarRentalSystem.backend.services;

import com.bmwCarRentalSystem.backend.enums.BookingStatus;
import com.bmwCarRentalSystem.backend.model.Booking;
import com.bmwCarRentalSystem.backend.model.Customer;
import com.bmwCarRentalSystem.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    

    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }
    

    public List<Booking> getBookingsByCustomer(Customer customer) {
        return bookingRepository.findByCustomer(customer);
    }
    

    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
    

    public Page<Booking> getBookingsWithPagination(Pageable pageable) {
        return bookingRepository.findAll(pageable);
    }
    

    public Booking updateBooking(Long id, Booking bookingDetails) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            Booking existing = booking.get();
            existing.setCustomer(bookingDetails.getCustomer());
            existing.setCar(bookingDetails.getCar());
            existing.setStartDate(bookingDetails.getStartDate());
            existing.setEndDate(bookingDetails.getEndDate());
            existing.setTotalAmount(bookingDetails.getTotalAmount());
            existing.setStatus(bookingDetails.getStatus());
            return bookingRepository.save(existing);
        }
        return null;
    }
    

    public boolean deleteBooking(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}