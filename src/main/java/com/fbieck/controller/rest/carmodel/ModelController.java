package com.fbieck.controller.rest.carmodel;

import com.fbieck.entities.carmodel.Model;
import com.fbieck.service.carmodel.model.IModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModelController {

    @Autowired
    private IModelService modelService;

    @RequestMapping(value = "/model/all")
    private Iterable<Model> findAll() {
        return modelService.findAll();
    }
}
