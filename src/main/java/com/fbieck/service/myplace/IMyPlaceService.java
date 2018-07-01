package com.fbieck.service.myplace;

import com.fbieck.entities.MyPlace;

public interface IMyPlaceService {

    Iterable<MyPlace> findAll();
}
