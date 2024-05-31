package org.example.repos;

import org.example.models.Offer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class OfferRepositoryImpl implements OfferRepository {
    private final List<Offer> offers = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    @Override
    public List<Offer> findAll() {
        return new ArrayList<>(offers);
    }

    @Override
    public Optional<Offer> findById(int id) {
        return offers.stream().filter(offer -> offer.getId() == id).findFirst();
    }

    @Override
    public void save(Offer offer) {
        offer.setId((long) counter.incrementAndGet());
        offers.add(offer);
    }

    @Override
    public void update(Offer offer) {
        delete(Math.toIntExact(offer.getId()));
        offers.add(offer);
    }

    @Override
    public void delete(int id) {
        offers.removeIf(offer -> offer.getId() == id);
    }

}
