package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.Motor;
import com.fbieck.service.carmodel.motor.IMotorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorController {

    @Autowired
    private IMotorService motorService;

    @RequestMapping(value = "/motor/all")
    private Iterable<Motor> findAll() {
        return motorService.findAll();
    }
}
