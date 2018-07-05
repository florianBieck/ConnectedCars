package com.fbieck.repositories;

import com.fbieck.entities.MyPlace;
import com.fbieck.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface MyPlaceRepository extends CrudRepository<MyPlace, Integer> {

    Iterable<MyPlace> findAllByUser(User user);
}
