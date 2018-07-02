package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.EnergyEfficiencyClass;
import com.fbieck.service.carmodel.energyefficiencyclass.IEnergyEfficiencyClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnergyEfficiencyClassController {

    @Autowired
    private IEnergyEfficiencyClassService energyEfficiencyClassService;

    @RequestMapping(value = "/energyefficiencyclass/all")
    private Iterable<EnergyEfficiencyClass> findAll() {
        return energyEfficiencyClassService.findAll();
    }
}
