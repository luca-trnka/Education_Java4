package org.example.dtos;

import java.util.List;

public class SupplierDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> offerIds;
    private List<Long> workerIds;

    public SupplierDTO() {
    }

    public SupplierDTO(Long id, String name, String email, List<Long> offerIds, List<Long> workerIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.offerIds = offerIds;
        this.workerIds = workerIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Long> getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(List<Long> offerIds) {
        this.offerIds = offerIds;
    }

    public List<Long> getWorkerIds() {
        return workerIds;
    }

    public void setWorkerIds(List<Long> workerIds) {
        this.workerIds = workerIds;
    }
}
