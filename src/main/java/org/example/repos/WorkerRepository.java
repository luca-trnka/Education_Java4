package org.example.repos;

import org.example.models.Worker;
import java.util.List;
import java.util.Optional;

public interface WorkerRepository {
    List<Worker> findAll();
    Optional<Worker> findById(int id);
    void save(Worker worker);
    void update(Worker worker);
    void delete(int id);
}