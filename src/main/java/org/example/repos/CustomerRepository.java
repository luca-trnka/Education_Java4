package org.example.repos;

import org.example.models.Customer;
import java.util.List;
import java.util.Optional;


public interface CustomerRepository {
    List<Customer> findAll();
    Optional<Customer> findById(int id);
    Optional<Customer> findByName(String name);
    Optional<Customer> findByEmail(String email);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
    boolean existsById(int id);
}