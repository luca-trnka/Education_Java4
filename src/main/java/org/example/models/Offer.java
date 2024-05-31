package org.example.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToMany
    private List<Worker> workers;

    public Offer() {}

    public Offer(String description, OfferStatus status, Customer customer, Supplier supplier, List<Worker> workers) {
        this.description = description;
        this.status = status;
        this.customer = customer;
        this.supplier = supplier;
        this.workers = workers;
    }

    public Offer(Long id, String description, OfferStatus status, Customer customer, Supplier supplier, List<Worker> workers) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.customer = customer;
        this.supplier = supplier;
        this.workers = workers;
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

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
