package org.example.services;

import org.example.models.Offer;
import org.example.models.OfferStatus;
import org.example.repos.OfferRepository;
import org.example.repos.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final WorkerRepository workerRepository;

    public OfferService(OfferRepository offerRepository, WorkerRepository workerRepository) {
        this.offerRepository = offerRepository;
        this.workerRepository = workerRepository;
    }

    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Optional<Offer> getOfferById(int id) {
        return offerRepository.findById(id);
    }

    public void createOffer(Offer offer) {
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
}
