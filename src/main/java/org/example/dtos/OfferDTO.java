package org.example.dtos;

import org.example.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OfferDTO {
    private Long id;
    private String description;
    private String status;
    private Long customerId;
    private Long supplierId;
    private List<Long> workerIds;

    public OfferDTO() {}

    public OfferDTO(Long id, String description, String status, Long customerId, Long supplierId, List<Long> workerIds) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.customerId = customerId;
        this.supplierId = supplierId;
        this.workerIds = workerIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<Long> getWorkerIds() {
        return workerIds;
    }

    public void setWorkerIds(List<Long> workerIds) {
        this.workerIds = workerIds;
    }

    public static OfferDTO fromEntity(Offer offer) {
        return new OfferDTO(
                offer.getId(),
                offer.getDescription(),
                offer.getStatus().name(),
                offer.getCustomer().getId(),
                offer.getSupplier().getId(),
                offer.getWorkers().stream().map(worker -> worker.getId()).collect(Collectors.toList())
        );
    }

    public Offer toEntity() {
        Offer offer = new Offer();
        offer.setId(this.id);
        offer.setDescription(this.description);
        offer.setStatus(OfferStatus.valueOf(this.status));

        if (this.customerId != null) {
            Customer customer = new Customer();
            customer.setId(this.customerId);
            offer.setCustomer(customer);
        }

        if (this.supplierId != null) {
            Supplier supplier = new Supplier();
            supplier.setId(this.supplierId);
            offer.setSupplier(supplier);
        }

        List<Worker> workers = new ArrayList<>();
        if (this.workerIds != null) {
            for (Long workerId : this.workerIds) {
                Worker worker = new Worker();
                worker.setId(workerId);
                workers.add(worker);
            }
        }
        offer.setWorkers(workers);

        return offer;
    }
}
