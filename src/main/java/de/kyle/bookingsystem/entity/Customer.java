package de.kyle.bookingsystem.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author kyleonaut
 */
@Data
@Entity
public class Customer {

    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;
}
