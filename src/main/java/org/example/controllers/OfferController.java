package org.example.controllers;

import org.example.exceptions.ResourceNotFoundException;
import org.example.dtos.OfferDTO;
import org.example.models.Offer;
import org.example.models.OfferStatus;
import org.example.services.OfferService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offers")
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
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
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
    }

    @PostMapping
    public OfferDTO createOffer(@RequestBody OfferDTO offerDTO) {
        Offer offer = offerDTO.toEntity();
        offerService.createOffer(offer);
        return OfferDTO.fromEntity(offer);
    }

    @PutMapping("/{id}")
    public void updateOffer(@PathVariable Long id, @RequestBody OfferDTO offerDTO) {
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
                .orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
    }

    @PutMapping("/{id}/status")
    public void updateOfferStatus(@PathVariable Long id, @RequestParam OfferStatus status) {
        offerService.updateOfferStatus(Math.toIntExact(id), status);
    }

    @PostMapping("/{id}/workers/{workerId}")
    public void addWorkerToOffer(@PathVariable Long id, @PathVariable Long workerId) {
        offerService.addWorkerToOffer(Math.toIntExact(id), Math.toIntExact(workerId));
    }

    @DeleteMapping("/{id}/workers/{workerId}")
    public void removeWorkerFromOffer(@PathVariable Long id, @PathVariable Long workerId) {
        offerService.removeWorkerFromOffer(Math.toIntExact(id), Math.toIntExact(workerId));
    }
}