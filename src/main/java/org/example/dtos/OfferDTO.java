package org.example.dtos;

import java.util.List;

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

    // Getters and Setters
}
