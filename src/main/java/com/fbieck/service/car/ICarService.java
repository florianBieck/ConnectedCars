package com.fbieck.service.car;

import com.fbieck.dto.CarQuote;
import com.fbieck.entities.Car;
import com.fbieck.entities.User;

public interface ICarService {

    Iterable<Car> findAll();

    Iterable<Car> findAllByUser(User user);

    Iterable<CarQuote> findAllQutesByUser(User user);

    Car createCar(User user, Integer identifier, String title);
}
