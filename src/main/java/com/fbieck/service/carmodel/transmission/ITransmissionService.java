package com.fbieck.service.carmodel.transmission;

import com.fbieck.entities.carmodel.Transmission;

public interface ITransmissionService {

    Iterable<Transmission> findAll();
}
