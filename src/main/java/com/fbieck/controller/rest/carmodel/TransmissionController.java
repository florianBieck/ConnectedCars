package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.Transmission;
import com.fbieck.service.carmodel.transmission.ITransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransmissionController {

    @Autowired
    private ITransmissionService transmissionService;

    @RequestMapping(value = "/transmission/all")
    private Iterable<Transmission> findAll() {
        return transmissionService.findAll();
    }
}
