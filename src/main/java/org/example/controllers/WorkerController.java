package org.example.controllers;

import org.example.dtos.WorkerDTO;
import org.example.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public ResponseEntity<WorkerDTO> createWorker(@RequestBody WorkerDTO workerDTO) {
        WorkerDTO createdWorker = workerService.createWorker(workerDTO);
        return ResponseEntity.status(201).body(createdWorker);
    }

    @GetMapping
    public ResponseEntity<List<WorkerDTO>> getAllWorkers() {
        List<WorkerDTO> workers = workerService.getAllWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/{workerId}")
    public ResponseEntity<WorkerDTO> getWorkerById(@PathVariable Long workerId) {
        WorkerDTO worker = workerService.getWorkerById(workerId);
        return ResponseEntity.ok(worker);
    }

    @PutMapping("/{workerId}")
    public ResponseEntity<WorkerDTO> updateWorker(@PathVariable Long workerId, @RequestBody WorkerDTO workerDTO) {
        WorkerDTO updatedWorker = workerService.updateWorker(workerId, workerDTO);
        return ResponseEntity.ok(updatedWorker);
    }

    @DeleteMapping("/{workerId}")
    public ResponseEntity<Void> deleteWorker(@PathVariable Long workerId) {
        workerService.deleteWorker(workerId);
        return ResponseEntity.noContent().build();
    }
}
