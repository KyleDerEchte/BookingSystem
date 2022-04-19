package de.kyle.bookingsystem.repository;

import de.kyle.bookingsystem.entity.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * @author kyleonaut
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findCustomerById(long id);

    Customer findCustomerBySurname(String name);

    Customer findCustomerByForenameAndSurname(String forename, String surname);
}
