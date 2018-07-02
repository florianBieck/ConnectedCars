package com.fbieck.service.carmodel.transmission;

import com.fbieck.entities.carmodel.Transmission;
import com.fbieck.repositories.carmodel.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransmissionService implements ITransmissionService {

    @Autowired
    private TransmissionRepository transmissionRepository;

    @Override
    public Iterable<Transmission> findAll() {
        return transmissionRepository.findAll();
    }
}
