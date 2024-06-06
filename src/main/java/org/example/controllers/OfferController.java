package org.example.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.example.exceptions.ResourceNotFoundException;
import org.example.dtos.OfferDTO;
import org.example.models.Offer;
import org.example.models.OfferStatus;
import org.example.services.CustomerService;
import org.example.services.OfferService;
import org.example.services.SupplierService;
import org.example.services.WorkerService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;
    private final WorkerService workerService;
    private final CustomerService customerService;
    private final SupplierService supplierService;

    public OfferController(OfferService offerService, WorkerService workerService, CustomerService customerService, SupplierService supplierService) {
        this.offerService = offerService;
        this.workerService = workerService;
        this.customerService = customerService;
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<OfferDTO> getAllOffers() {
        return offerService.getAllOffers().stream()
                .map(OfferDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public OfferDTO getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(Math.toIntExact(id))
                .map(OfferDTO::fromEntity)
                .orElseThrow(() -> new ResourceNotFoundException("Offer with id " + id + " not found"));
    }

    @PostMapping
    public OfferDTO createOffer(@RequestParam Long supplierId, @RequestParam Long customerId, @RequestBody @Valid OfferDTO offerDTO) {
        if (!supplierService.supplierExists(Math.toIntExact(supplierId))) {
            throw new ResourceNotFoundException("Supplier with id " + supplierId + " not found");
        }
        if (!customerService.customerExists(Math.toIntExact(customerId))) {
            throw new ResourceNotFoundException("Customer with id " + customerId + " not found");
        }
        Offer offer = offerDTO.toEntity();
        offerService.createOffer(supplierId, customerId, offer);
        return OfferDTO.fromEntity(offer);
    }

    @PutMapping("/{id}")
    public void updateOffer(@PathVariable Long id, @RequestBody OfferDTO offerDTO) {
        if (!offerService.offerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Offer with id " + id + " not found");
        }
        Offer offer = offerDTO.toEntity();
        offer.setId(id);
        offerService.updateOffer(offer);
    }

    @DeleteMapping("/{id}")
    public void deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(Math.toIntExact(id));
    }

    @GetMapping("/{id}/status")
    public OfferStatus getOfferStatus(@PathVariable Long id) {
        return offerService.getOfferStatus(Math.toIntExact(id))
                .orElseThrow(() -> new ResourceNotFoundException("Offer with id \" + id + \" not found"));
    }

    @PutMapping("/{id}/status")
    public void updateOfferStatus(@PathVariable Long id, @RequestParam OfferStatus status) {
        if (!offerService.offerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Offer with id " + id + " not found");
        }
        if (!Arrays.asList(OfferStatus.values()).contains(status)) {
            throw new IllegalArgumentException("Invalid status value: " + status);
        }
        offerService.updateOfferStatus(Math.toIntExact(id), status);
    }

    @PostMapping("/{id}/workers/{workerId}")
    public void addWorkerToOffer(@PathVariable Long id, @PathVariable Long workerId) {
        if (!offerService.offerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Offer with id " + id + " not found");
        }
        if (!workerService.workerExists(Math.toIntExact(workerId))) {
            throw new ResourceNotFoundException("Worker with id " + workerId + " not found");
        }
        offerService.addWorkerToOffer(Math.toIntExact(id), Math.toIntExact(workerId));
    }

    @DeleteMapping("/{id}/workers/{workerId}")
    public void removeWorkerFromOffer(@PathVariable Long id, @PathVariable Long workerId) {
        if (!offerService.offerExists(Math.toIntExact(id))) {
            throw new ResourceNotFoundException("Offer with id " + id + " not found");
        }
        if (!workerService.workerExists(Math.toIntExact(workerId))) {
            throw new ResourceNotFoundException("Worker with id " + workerId + " not found");
        }
        offerService.removeWorkerFromOffer(Math.toIntExact(id), Math.toIntExact(workerId));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}