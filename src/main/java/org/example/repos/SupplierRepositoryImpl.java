package org.example.repos;

import org.example.models.Supplier;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class SupplierRepositoryImpl implements SupplierRepository {
    private final List<Supplier> suppliers = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Supplier> findAll() {
        return new ArrayList<>(suppliers);
    }

    @Override
    public Optional<Supplier> findById(int id) {
        return suppliers.stream().filter(supplier -> supplier.getId() == id).findFirst();
    }

    @Override
    public void save(Supplier supplier) {
        supplier.setId((long) counter.incrementAndGet());
        suppliers.add(supplier);
    }

    @Override
    public void update(Supplier supplier) {
        delete(Math.toIntExact(supplier.getId()));
        suppliers.add(supplier);
    }

    @Override
    public void delete(int id) {
        suppliers.removeIf(supplier -> supplier.getId() == id);
    }
}