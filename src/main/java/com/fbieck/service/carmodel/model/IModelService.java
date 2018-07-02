package com.fbieck.service.carmodel.model;

import com.fbieck.entities.carmodel.Model;

public interface IModelService {

    Iterable<Model> findAll();
}
