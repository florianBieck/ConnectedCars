package com.fbieck.service.trip;

import com.fbieck.entities.Trip;

public interface ITripService {

    Iterable<Trip> findAll();
}
