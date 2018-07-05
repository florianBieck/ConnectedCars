package com.fbieck.controller.rest;

import com.fbieck.entities.Trip;
import com.fbieck.service.trip.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TripController {

    @Autowired
    private ITripService tripService;

    @RequestMapping(value = "/trip/all")
    private Iterable<Trip> findAll() {
        return tripService.findAll();
    }
}
