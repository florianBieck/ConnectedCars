package com.fbieck.controller.rest;

import com.fbieck.entities.MyPlace;
import com.fbieck.service.myplace.IMyPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPlaceController {

    @Autowired
    private IMyPlaceService myPlaceService;

    @RequestMapping(value = "/place/all")
    private Iterable<MyPlace> findAll(){
        return myPlaceService.findAll();
    }
}
