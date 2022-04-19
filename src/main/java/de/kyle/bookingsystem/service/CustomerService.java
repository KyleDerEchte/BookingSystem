package de.kyle.bookingsystem.service;

import de.kyle.bookingsystem.entity.Customer;
import de.kyle.bookingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kyleonaut
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer) {
        final Customer byForenameAndSurname = this.customerRepository.findCustomerByForenameAndSurname(customer.getForename(), customer.getSurname());
        if (byForenameAndSurname != null){
            return byForenameAndSurname;
        }
        this.customerRepository.save(customer);
        return customer;
    }

    public boolean deleteCustomerById(long id) {
        final Customer customer = this.customerRepository.findCustomerById(id);
        if (customer == null) {
            return false;
        }
        this.customerRepository.delete(customer);
        return true;
    }

    public Customer getCustomerByForenameAndSurname(String forename, String surname) {
        return this.customerRepository.findCustomerByForenameAndSurname(forename, surname);
    }

    public Customer getCustomerById(long id) {
        return this.customerRepository.findCustomerById(id);
    }

    public List<Customer> getAllCustomers() {
        final List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }
}
