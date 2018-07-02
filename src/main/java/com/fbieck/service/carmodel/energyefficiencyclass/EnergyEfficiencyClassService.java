package com.fbieck.service.carmodel.energyefficiencyclass;

import com.fbieck.entities.carmodel.EnergyEfficiencyClass;
import com.fbieck.repositories.carmodel.EnergyEfficiencyClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnergyEfficiencyClassService implements IEnergyEfficiencyClassService {

    @Autowired
    private EnergyEfficiencyClassRepository energyEfficiencyClassRepository;

    @Override
    public Iterable<EnergyEfficiencyClass> findAll() {
        return energyEfficiencyClassRepository.findAll();
    }
}
