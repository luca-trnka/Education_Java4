package org.example.dtos;


import java.util.List;

public class WorkerDTO {
    private Long id;
    private String name;
    private String email;
    private List<Long> offerIds;
    private Long supplierId;

    public WorkerDTO() {}

    public WorkerDTO(Long id, String name, String email, List<Long> offerIds, Long supplierId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.offerIds = offerIds;
        this.supplierId = supplierId;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}