package com.fbieck.repositories;

import com.fbieck.entities.Trip;
import com.fbieck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Integer> {

    Iterable<Trip> findAllByUser(User user);
}
