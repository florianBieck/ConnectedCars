package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.EmissionClass;
import com.fbieck.service.carmodel.emissionclass.IEmissionClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmissionClassController {


    @Autowired
    private IEmissionClassService emissionClassService;

    @RequestMapping(value = "/emissionclass/all")
    private Iterable<EmissionClass> findAll() {
        return emissionClassService.findAll();
    }
}
