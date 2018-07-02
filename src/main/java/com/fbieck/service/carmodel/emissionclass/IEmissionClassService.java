package com.fbieck.service.carmodel.emissionclass;

import com.fbieck.entities.carmodel.EmissionClass;

public interface IEmissionClassService {

    Iterable<EmissionClass> findAll();
}
