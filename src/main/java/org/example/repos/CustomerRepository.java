package org.example.repos;

import org.example.models.Customer;
import java.util.List;
import java.util.Optional;


public interface CustomerRepository {
    List<Customer> findAll();
    Optional<Customer> findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}