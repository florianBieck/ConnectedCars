package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.CylinderArrangement;
import com.fbieck.service.carmodel.cylinderarrangement.ICylinderArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CylinderArrangementController {

    @Autowired
    private ICylinderArrangementService cylinderArrangementService;

    @RequestMapping(value = "/cylinderarrangement/all")
    private Iterable<CylinderArrangement> findAll() {
        return cylinderArrangementService.findAll();
    }
}
