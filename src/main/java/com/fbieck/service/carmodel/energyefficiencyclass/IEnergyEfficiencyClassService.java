package com.fbieck.service.carmodel.energyefficiencyclass;

import com.fbieck.entities.carmodel.EnergyEfficiencyClass;

public interface IEnergyEfficiencyClassService {

    Iterable<EnergyEfficiencyClass> findAll();
}
