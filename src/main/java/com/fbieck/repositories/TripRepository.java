package com.fbieck.repositories;

import com.fbieck.entities.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Integer> {
}
