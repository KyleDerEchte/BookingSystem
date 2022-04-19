package de.kyle.bookingsystem.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author kyleonaut
 */
@Data
@Entity(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Customer customer;

    @Column(name = "date")
    private LocalDateTime date;
}
