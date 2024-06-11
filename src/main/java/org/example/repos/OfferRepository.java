package org.example.repos;

import org.example.models.Offer;
import java.util.List;
import java.util.Optional;

public interface OfferRepository {
    List<Offer> findAll();
    Optional<Offer> findById(int id);
    void save(Offer offer);
    void update(Offer offer);
    void delete(int id);
    boolean existsById(int id);
}