package com.fbieck.service.myroute;

import com.fbieck.entities.Car;
import com.fbieck.entities.MyPlace;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;

import java.util.Date;

public interface IMyRouteService {

    Iterable<MyRoute> findAll();

    Iterable<MyRoute> findAllByUser(User user);

    Iterable<MyRoute> findAllByCar(Car car);

    Integer countAllByUser(User user);

    Integer countAllByCar(Car car);

    MyRoute createMyRoute(User user, MyPlace start, MyPlace end, String title, Date timestamp);
}
