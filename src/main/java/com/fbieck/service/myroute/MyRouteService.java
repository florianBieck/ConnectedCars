package com.fbieck.service.myroute;

import com.fbieck.entities.Car;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import com.fbieck.repositories.MyRouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRouteService implements IMyRouteService{

    @Autowired
    private MyRouteRepository myRouteRepository;

    @Override
    public Iterable<MyRoute> findAll() {
        return myRouteRepository.findAll();
    }

    @Override
    public Iterable<MyRoute> findAllByUser(User user) {
        return myRouteRepository.findAllByUser(user);
    }

    @Override
    public Iterable<MyRoute> findAllByCar(Car car) {
        return myRouteRepository.findAllByCar(car);
    }

    @Override
    public Integer countAllByUser(User user) {
        return myRouteRepository.countAllByUser(user);
    }

    @Override
    public Integer countAllByCar(Car car) {
        return myRouteRepository.countAllByCar(car);
    }
}
