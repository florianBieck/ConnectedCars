package com.fbieck.service.trip;

import com.fbieck.entities.Trip;
import com.fbieck.entities.User;

public interface ITripService {

    Iterable<Trip> findAll();

    Iterable<Trip> findAllByUser(User user);
}
