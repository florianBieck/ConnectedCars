package com.fbieck.service.myplace;

import com.fbieck.entities.MyPlace;
import com.fbieck.entities.User;
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

    @Override
    public Iterable<MyPlace> findAllByUser(User user) {
        return myPlaceRepository.findAllByUser(user);
    }

    @Override
    public MyPlace createMyPlace(User user, Double latitude, Double longitude, String title) {
        MyPlace myPlace = new MyPlace();
        myPlace.setUser(user);
        myPlace.setLatitude(latitude);
        myPlace.setLongitude(longitude);
        myPlace.setTitle(title);
        return myPlaceRepository.save(myPlace);
    }
}
