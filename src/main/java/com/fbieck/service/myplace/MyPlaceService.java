package com.fbieck.service.myplace;

import com.fbieck.entities.MyPlace;
import com.fbieck.repositories.MyPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPlaceService implements IMyPlaceService{

    @Autowired
    private MyPlaceRepository myPlaceRepository;

    @Override
    public Iterable<MyPlace> findAll() {
        return myPlaceRepository.findAll();
    }
}
