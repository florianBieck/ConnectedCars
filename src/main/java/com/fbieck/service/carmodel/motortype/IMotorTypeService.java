package com.fbieck.service.carmodel.motortype;

import com.fbieck.entities.carmodel.MotorType;

public interface IMotorTypeService {

    Iterable<MotorType> findAll();
}
