package org.example.repos;

import org.example.models.Customer;
import org.example.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerRepositoryImpl implements CustomerRepository {
    private final List<Customer> customers = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public Optional<Customer> findById(int id) {
        return customers.stream().filter(customer -> customer.getId() == id).findFirst();
    }

    @Override
    public void save(Customer customer) {
        customer.setId((long) counter.incrementAndGet());
        customers.add(customer);

    }

    @Override
    public void update(Customer customer) {
        delete(Math.toIntExact(customer.getId()));
        customers.add(customer);
    }

    @Override
    public void delete(int id) {
        customers.removeIf(customer -> customer.getId() == id);
    }
}
