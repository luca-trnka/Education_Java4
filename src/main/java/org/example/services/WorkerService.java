package org.example.services;

import org.example.models.Supplier;
import org.example.models.Worker;
import org.example.repos.SupplierRepository;
import org.example.repos.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    public Optional<Worker> getWorkerById(int id) {
        return workerRepository.findById(id);
    }

    public void createWorker(Long supplierId,Worker worker) {
        Supplier supplier = supplierRepository.findById(Math.toIntExact(supplierId))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        worker.setSupplier(supplier);
        supplier.getWorkers().add(worker);

        workerRepository.save(worker);
    }

    public void updateWorker(Worker worker) {
        workerRepository.update(worker);
    }

    public void deleteWorker(int id) {
        workerRepository.delete(id);
    }
}