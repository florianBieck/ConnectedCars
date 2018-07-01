package com.fbieck.repositories;

import com.fbieck.entities.MyPlace;
import org.springframework.data.repository.CrudRepository;

public interface MyPlaceRepository extends CrudRepository<MyPlace, Integer> {
}
