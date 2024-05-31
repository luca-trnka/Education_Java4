package org.example.controllers;

import org.example.exceptions.ResourceNotFoundException;
import org.example.dtos.WorkerDTO;
import org.example.models.Worker;
import org.example.services.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/workers")
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping
    public List<WorkerDTO> getAllWorkers() {
        return workerService.getAllWorkers().stream()
                .map(WorkerDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public WorkerDTO getWorkerById(@PathVariable Long id) {
        return workerService.getWorkerById(Math.toIntExact(id))
                .map(WorkerDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Worker not found"));
    }

    @PostMapping
    public WorkerDTO createWorker(@RequestParam Long supplierId, @RequestBody WorkerDTO workerDTO) {
        Worker worker = workerDTO.toEntity();
        workerService.createWorker(supplierId, worker);
        return WorkerDTO.fromEntity(worker);
    }

    @PutMapping("/{id}")
    public void updateWorker(@PathVariable Long id, @RequestBody WorkerDTO workerDTO) {
        Worker worker = workerDTO.toEntity();
        worker.setId(id);
        workerService.updateWorker(worker);
    }

    @DeleteMapping("/{id}")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(Math.toIntExact(id));
    }
}