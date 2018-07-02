package com.fbieck.repositories;

import com.fbieck.entities.Car;
import com.fbieck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Integer> {

    Iterable<Car> findAllByUser(User user);
}
