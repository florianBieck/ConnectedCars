package com.fbieck.repositories;

import com.fbieck.entities.Car;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface MyRouteRepository extends CrudRepository<MyRoute, Integer> {

    Iterable<MyRoute> findAllByUser(User user);

    Iterable<MyRoute> findAllByCar(Car car);

    Integer countAllByUser(User user);

    Integer countAllByCar(Car car);
}
