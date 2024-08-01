package dev.pedrodias.inventory_management.controller;

import dev.pedrodias.inventory_management.model.Customer;
import dev.pedrodias.inventory_management.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/customers"})
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = this.customerService.getAllCustomers();
        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
        Customer customer = this.customerService.getCustomerById(id);
        return customer != null ? new ResponseEntity(customer, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);
        return new ResponseEntity(createdCustomer, HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = this.customerService.updateCustomer(id, customer);
        return updatedCustomer != null ? new ResponseEntity(updatedCustomer, HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Long id) {
        this.customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}