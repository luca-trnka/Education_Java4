package org.example.services;

import org.example.models.Customer;
import org.example.repos.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.findById(id);
    }

    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer customer) {
        customerRepository.update(customer);
    }

    public void deleteCustomer(int id) {
        customerRepository.delete(id);
    }

    public boolean customerExists(int id) {
        return customerRepository.existsById(id);
    }

    public boolean customerExistsByName(String name) {
        return customerRepository.findByName(name).isPresent();
    }

    public boolean customerExistsByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }
}