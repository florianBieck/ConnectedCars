package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.MotorType;
import com.fbieck.service.carmodel.motortype.IMotorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotorTypeController {

    @Autowired
    private IMotorTypeService motorTypeService;

    @RequestMapping(value = "/motortype/all")
    private Iterable<MotorType> findAll() {
        return motorTypeService.findAll();
    }
}
