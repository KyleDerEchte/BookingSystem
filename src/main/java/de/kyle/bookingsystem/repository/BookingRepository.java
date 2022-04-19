package de.kyle.bookingsystem.repository;

import de.kyle.bookingsystem.entity.Booking;
import de.kyle.bookingsystem.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author kyleonaut
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    Booking findBookingById(long id);

    Booking findBookingByDate(LocalDateTime date);

    List<Booking> findBookingsByDateIsBetween(LocalDateTime start, LocalDateTime end);

    List<Booking> findBookingsByCustomer(Customer customer);
}

