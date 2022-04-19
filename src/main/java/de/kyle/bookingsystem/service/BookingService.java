package de.kyle.bookingsystem.service;

import de.kyle.bookingsystem.entity.Booking;
import de.kyle.bookingsystem.entity.Customer;
import de.kyle.bookingsystem.model.BookingRequest;
import de.kyle.bookingsystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kyleonaut
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BookingService {
    private final BookingRepository bookingRepository;
    private final CustomerService customerService;

    public ResponseEntity<Booking> saveBooking(BookingRequest request) {
        if (request == null) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        final Booking booking = new Booking();
        Customer customer = this.customerService.getCustomerByForenameAndSurname(request.getForename(), request.getSurname());
        if (customer == null) {
            customer = new Customer();
            customer.setForename(request.getForename());
            customer.setSurname(request.getSurname());
            this.customerService.saveCustomer(customer);
        }
        booking.setCustomer(customer);
        booking.setDate(request.getDate());
        this.bookingRepository.save(booking);
        return new ResponseEntity<>(booking, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<Booking>> getAllBookings() {
        final List<Booking> bookings = new ArrayList<>();
        this.bookingRepository.findAll().forEach(bookings::add);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    public boolean deleteBookingById(long id) {
        final Booking bookingById = this.bookingRepository.findBookingById(id);
        if (bookingById == null) {
            return false;
        }
        this.bookingRepository.delete(bookingById);
        return true;
    }

    public Booking getBookingById(long id) {
        return this.bookingRepository.findBookingById(id);
    }

    public List<Booking> getBookingsForDate(LocalDateTime date) {
        return this.bookingRepository.findBookingsByDateIsBetween(date.minusHours(date.getHour()), date.plusHours(24 - date.getHour()));
    }
}
