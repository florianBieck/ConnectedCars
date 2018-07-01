package com.fbieck.service.car;

import com.fbieck.dto.CarQuote;
import com.fbieck.entities.Car;
import com.fbieck.entities.User;
import com.fbieck.repositories.CarRepository;
import com.fbieck.service.myroute.IMyRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private IMyRouteService myRouteService;

    @Override
    public Iterable<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Iterable<Car> findAllByUser(User user) {
        return carRepository.findAllByUser(user);
    }

    @Override
    public Iterable<CarQuote> findAllQutesByUser(User user) {
        Collection<Car> cars = (Collection<Car>) findAllByUser(user);
        ArrayList<CarQuote> carQuotes = new ArrayList<>();
        cars.forEach( car -> {
            CarQuote carQuote = new CarQuote();
            carQuote.setCar(car);
            int all = myRouteService.countAllByUser(user);
            int count = myRouteService.countAllByCar(car);
            float percentage = all > 0 ? count/all : 0;
            System.out.println(all+" "+count+" "+percentage+" "+user.getEmail()+" "+car.getTitle());
            carQuote.setPercentage(percentage);
        });
        return carQuotes;
    }

    @Override
    public Car createCar(User user, String identifier, String title) {
        Car car = new Car();
        car.setUser(user);
        car.setId(identifier);
        car.setTitle(title);
        car.setLatitude(ThreadLocalRandom.current().nextDouble(-75, 75));
        car.setLongitude(ThreadLocalRandom.current().nextDouble(-75, 75));
        return carRepository.save(car);
    }
}
