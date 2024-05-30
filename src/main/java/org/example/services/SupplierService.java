package org.example.services;

import org.example.models.Supplier;
import org.example.repos.SupplierRepository;
import java.util.List;
import java.util.Optional;


public class SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

    public void createSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public void updateSupplier(Supplier supplier) {
        supplierRepository.update(supplier);
    }

    public void deleteSupplier(int id) {
        supplierRepository.delete(id);
    }
}