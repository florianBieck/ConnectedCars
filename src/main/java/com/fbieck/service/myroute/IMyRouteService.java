package com.fbieck.service.myroute;

import com.fbieck.entities.MyPlace;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;

public interface IMyRouteService {

    Iterable<MyRoute> findAll();

    Iterable<MyRoute> findAllByUser(User user);

    Integer countAllByUser(User user);

    MyRoute createMyRoute(User user, MyPlace start, MyPlace end, String title);
}
