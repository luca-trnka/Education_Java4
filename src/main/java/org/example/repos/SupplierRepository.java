package org.example.repos;

import org.example.models.Supplier;
import java.util.List;
import java.util.Optional;

public interface SupplierRepository {
    List<Supplier> findAll();
    Optional<Supplier> findById(int id);
    void save(Supplier supplier);
    void update(Supplier supplier);
    void delete(int id);
    boolean existsById(int id);
}