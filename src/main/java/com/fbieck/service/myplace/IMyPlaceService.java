package com.fbieck.service.myplace;

import com.fbieck.entities.MyPlace;
import com.fbieck.entities.User;

public interface IMyPlaceService {

    Iterable<MyPlace> findAll();

    MyPlace createMyPlace(User user, Double latitude, Double longitude, String title);
}
