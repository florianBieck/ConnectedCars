package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.dto.CarQuote;
import com.fbieck.entities.Car;
import com.fbieck.service.car.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @Autowired
    private ICarService carService;

    @RequestMapping(value = "/car/all")
    private Iterable<Car> findAll(){
        return carService.findAll();
    }

    @GetMapping(value = "/car/bysession")
    private Iterable<Car> findAllBySession(){
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return carService.findAllByUser(
                    ((FFUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
            );
        }
        return null;
    }

    @GetMapping(value = "/carqoute/bysession")
    private Iterable<CarQuote> findAllCarQouteBySession(){
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return carService.findAllQutesByUser(
                    ((FFUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
            );
        }
        return null;
    }

    @PostMapping(value = "/car/create", params = {"identifier", "title"})
    private Car createCar(
            @RequestParam(value = "identifier") Integer identifier,
            @RequestParam(value = "title") String title
    ){
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return carService.createCar(
                    ((FFUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser(),
                    identifier,
                    title
            );
        }
        return null;
    }
}
