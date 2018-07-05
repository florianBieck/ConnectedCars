package com.fbieck.service.myroute;

import com.fbieck.entities.MyPlace;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import com.fbieck.repositories.MyRouteRepository;
import com.fbieck.service.car.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRouteService implements IMyRouteService{

    @Autowired
    private MyRouteRepository myRouteRepository;

    @Autowired
    private ICarService carService;

    @Override
    public Iterable<MyRoute> findAll() {
        return myRouteRepository.findAll();
    }

    @Override
    public Iterable<MyRoute> findAllByUser(User user) {
        return myRouteRepository.findAllByUser(user);
    }

    @Override
    public Integer countAllByUser(User user) {
        return myRouteRepository.countAllByUser(user);
    }

    @Override
    public MyRoute createMyRoute(User user, MyPlace start, MyPlace end, String title) {
        MyRoute myRoute = new MyRoute();
        myRoute.setUser(user);
        myRoute.setStart(start);
        myRoute.setEnd(end);
        myRoute.setTitle(title);
        return myRouteRepository.save(myRoute);
    }
}
