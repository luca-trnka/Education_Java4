package org.example.controllers;

import org.example.exceptions.ResourceNotFoundException;
import org.example.dtos.SupplierDTO;
import org.example.models.Supplier;
import org.example.services.SupplierService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<SupplierDTO> getAllSuppliers() {
        return supplierService.getAllSuppliers().stream()
                .map(SupplierDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SupplierDTO getSupplierById(@PathVariable Long id) {
        return supplierService.getSupplierById(Math.toIntExact(id))
                .map(SupplierDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));
    }

    @PostMapping
    public SupplierDTO createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierDTO.toEntity();
        supplierService.createSupplier(supplier);
        return SupplierDTO.fromEntity(supplier);
    }

    @PutMapping("/{id}")
    public void updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierDTO.toEntity();
        supplier.setId(id);
        supplierService.updateSupplier(supplier);
    }

    @DeleteMapping("/{id}")
    public void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(Math.toIntExact(id));
    }
}