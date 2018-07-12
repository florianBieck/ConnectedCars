package com.fbieck.service.trip;

import com.fbieck.entities.Car;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.Trip;
import com.fbieck.entities.User;
import com.fbieck.repositories.TripRepository;
import com.fbieck.service.car.ICarService;
import com.fbieck.service.myroute.IMyRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TripService implements ITripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private IMyRouteService myRouteService;

    @Autowired
    private ICarService carService;

    @Override
    public Iterable<Trip> findAll() {
        return tripRepository.findAll();
    }

    @Override
    public Iterable<Trip> findAllByUser(User user) {
        return tripRepository.findAllByUser(user);
    }

    @Override
    public Trip createTrip(User user, Integer idroute, Integer idcar, Double duration, Date timestamp) {
        MyRoute route = myRouteService.findById(idroute);
        Car car = carService.findById(idcar);
        if (route != null && car != null) {
            Trip trip = new Trip();
            trip.setUser(user);
            trip.setMyRoute(route);
            trip.setCar(car);
            trip.setDuration(duration);
            trip.setTimestamp(timestamp);
            return tripRepository.save(trip);
        }
        return null;
    }
}
