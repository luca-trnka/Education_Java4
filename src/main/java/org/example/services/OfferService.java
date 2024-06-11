package org.example.services;

import org.example.models.*;
import org.example.repos.CustomerRepository;
import org.example.repos.OfferRepository;
import org.example.repos.SupplierRepository;
import org.example.repos.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final WorkerRepository workerRepository;
    private final SupplierRepository supplierRepository;
    private final CustomerRepository customerRepository;

    public OfferService(OfferRepository offerRepository, WorkerRepository workerRepository, SupplierRepository supplierRepository, CustomerRepository customerRepository) {
        this.offerRepository = offerRepository;
        this.workerRepository = workerRepository;
        this.supplierRepository = supplierRepository;
        this.customerRepository = customerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(int id) {
        return offerRepository.findById(id);
    }

    public void createOffer(Long supplierId, Long customerId, Offer offer) {
        Supplier supplier = supplierRepository.findById(Math.toIntExact(supplierId))
                .orElseThrow(() -> new RuntimeException("Supplier not found"));
        Customer customer = customerRepository.findById(Math.toIntExact(customerId))
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        offer.setSupplier(supplier);
        offer.setCustomer(customer);

        supplier.getOffers().add(offer);
        customer.getOffers().add(offer);
        offerRepository.save(offer);
    }

    public void updateOffer(Offer offer) {
        offerRepository.update(offer);
    }

    public void deleteOffer(int id) {
        offerRepository.delete(id);
    }

    public Optional<OfferStatus> getOfferStatus(int offerId) {
        return offerRepository.findById(offerId).map(Offer::getStatus);
    }

    public void updateOfferStatus(int offerId, OfferStatus status) {
        offerRepository.findById(offerId).ifPresent(offer -> {
            offer.setStatus(status);
            offerRepository.update(offer);
        });
    }

    public void addWorkerToOffer(int offerId, int workerId) {
        offerRepository.findById(offerId).ifPresent(offer -> {
            workerRepository.findById(workerId).ifPresent(worker -> {
                offer.getWorkers().add(worker);
                offerRepository.update(offer);
            });
        });
    }

    public void removeWorkerFromOffer(int offerId, int workerId) {
        offerRepository.findById(offerId).ifPresent(offer -> {
            offer.getWorkers().removeIf(worker -> worker.getId() == workerId);
            offerRepository.update(offer);
        });
    }

    public boolean offerExists(int id) {
        return offerRepository.existsById(id);
    }
}
