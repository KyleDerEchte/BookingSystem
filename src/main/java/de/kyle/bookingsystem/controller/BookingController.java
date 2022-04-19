package de.kyle.bookingsystem.controller;

import de.kyle.bookingsystem.entity.Booking;
import de.kyle.bookingsystem.model.BookingRequest;
import de.kyle.bookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kyleonaut
 */
@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class BookingController {
    private final BookingService bookingService;

    @PutMapping
    public ResponseEntity<Booking> addBooking(@RequestBody BookingRequest request) {
        return this.bookingService.saveBooking(request);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return this.bookingService.getAllBookings();
    }

    @GetMapping()
    public ResponseEntity<List<Booking>> getAllBookingsForDay(@RequestParam(name = "date") String date) {
        return new ResponseEntity<>(this.bookingService.getBookingsForDate(LocalDateTime.parse(date)), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteBooking(@RequestParam(name = "id") long id) {
        final boolean wasDeleted = this.bookingService.deleteBookingById(id);
        if (wasDeleted) {
            return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable(name = "id") long id) {
        final Booking booking = this.bookingService.getBookingById(id);
        if (booking == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
