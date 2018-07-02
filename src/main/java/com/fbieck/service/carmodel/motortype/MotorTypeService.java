package com.fbieck.service.carmodel.motortype;

import com.fbieck.entities.carmodel.MotorType;
import com.fbieck.repositories.carmodel.MotorTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorTypeService implements IMotorTypeService {


    @Autowired
    private MotorTypeRepository motorTypeRepository;

    @Override
    public Iterable<MotorType> findAll() {
        return motorTypeRepository.findAll();
    }
}
