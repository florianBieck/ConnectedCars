package com.fbieck.service.carmodel.motor;

import com.fbieck.entities.carmodel.Motor;

public interface IMotorService {

    Iterable<Motor> findAll();
}
