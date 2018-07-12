package com.fbieck.service.trip;

import com.fbieck.entities.Trip;
import com.fbieck.entities.User;

import java.util.Date;

public interface ITripService {

    Iterable<Trip> findAll();

    Iterable<Trip> findAllByUser(User user);

    Trip createTrip(User user, Integer idroute, Integer idcar, Double duration, Date timestamp);
}
