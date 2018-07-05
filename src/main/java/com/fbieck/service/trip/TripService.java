package com.fbieck.service.trip;

import com.fbieck.entities.Trip;
import com.fbieck.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository tripRepository;

    @Override
    public Iterable<Trip> findAll() {
        return tripRepository.findAll();
    }
}
