package com.fbieck.service.carmodel.emissionclass;

import com.fbieck.entities.carmodel.EmissionClass;
import com.fbieck.repositories.carmodel.EmissionClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissionClassService implements IEmissionClassService {

    @Autowired
    private EmissionClassRepository emissionClassRepository;

    @Override
    public Iterable<EmissionClass> findAll() {
        return emissionClassRepository.findAll();
    }
}
