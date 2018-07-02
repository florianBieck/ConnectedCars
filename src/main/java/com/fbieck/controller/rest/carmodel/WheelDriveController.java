package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.WheelDrive;
import com.fbieck.service.carmodel.wheeldrive.IWheelDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WheelDriveController {

    @Autowired
    private IWheelDriveService wheelDriveService;

    @RequestMapping(value = "/wheeldrive/all")
    private Iterable<WheelDrive> findAll() {
        return wheelDriveService.findAll();
    }
}
