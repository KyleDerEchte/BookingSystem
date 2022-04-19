package de.kyle.bookingsystem.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kyleonaut
 */
@Data
public class BookingRequest {

    private String forename;

    private String surname;

    private LocalDateTime date;
}
