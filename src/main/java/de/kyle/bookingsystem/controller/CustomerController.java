package de.kyle.bookingsystem.controller;

import de.kyle.bookingsystem.entity.Customer;
import de.kyle.bookingsystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kyleonaut
 */
@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CustomerController {
    private final CustomerService customerService;

    @PutMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(this.customerService.saveCustomer(customer), HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteCustomerById(@RequestParam(name = "id") long id) {
        final boolean wasDeleted = this.customerService.deleteCustomerById(id);
        if (wasDeleted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(name = "id") long id) {
        final Customer customer = this.customerService.getCustomerById(id);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(this.customerService.getAllCustomers(), HttpStatus.OK);
    }
}
