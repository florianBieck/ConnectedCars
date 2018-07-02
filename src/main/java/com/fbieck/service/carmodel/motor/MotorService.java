package com.fbieck.service.carmodel.motor;

import com.fbieck.entities.carmodel.Motor;
import com.fbieck.repositories.carmodel.MotorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotorService implements IMotorService {

    @Autowired
    private MotorRepository motorRepository;

    @Override
    public Iterable<Motor> findAll() {
        return motorRepository.findAll();
    }
}
