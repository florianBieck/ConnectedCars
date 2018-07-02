package com.fbieck.service.carmodel.model;

import com.fbieck.entities.carmodel.Model;
import com.fbieck.repositories.carmodel.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService implements IModelService {

    @Autowired
    private ModelRepository modelRepository;


    @Override
    public Iterable<Model> findAll() {
        return null;
    }
}
