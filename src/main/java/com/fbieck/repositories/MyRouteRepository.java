package com.fbieck.repositories;

import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface MyRouteRepository extends CrudRepository<MyRoute, Integer> {

    Iterable<MyRoute> findAllByUser(User user);

    Integer countAllByUser(User user);
}
