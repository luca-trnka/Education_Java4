package org.example.repos;

import org.example.models.Worker;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class WorkerRepositoryImpl implements WorkerRepository {
    private final List<Worker> workers = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Worker> findAll() {
        return new ArrayList<>(workers);
    }

    @Override
    public Optional<Worker> findById(int id) {
        return workers.stream().filter(worker -> worker.getId() == id).findFirst();
    }

    @Override
    public Optional<Worker> findByName(String name) {
        return workers.stream().filter(customer -> customer.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<Worker> findByEmail(String email) {
        return workers.stream().filter(customer -> customer.getEmail().equals(email)).findFirst();
    }

    @Override
    public void save(Worker worker) {
        worker.setId((long) counter.incrementAndGet());
        workers.add(worker);
    }

    @Override
    public void update(Worker worker) {
        delete(Math.toIntExact(worker.getId()));
        workers.add(worker);
    }

    @Override
    public void delete(int id) {
        workers.removeIf(worker -> worker.getId() == id);
    }

    @Override
    public boolean existsById(int id) {
        return workers.stream().anyMatch(offer -> offer.getId() == id);
    }
}