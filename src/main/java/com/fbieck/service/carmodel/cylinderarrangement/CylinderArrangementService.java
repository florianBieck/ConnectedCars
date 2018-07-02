package com.fbieck.service.carmodel.cylinderarrangement;

import com.fbieck.entities.carmodel.CylinderArrangement;
import com.fbieck.repositories.carmodel.CylinderArrangementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CylinderArrangementService implements ICylinderArrangementService {

    @Autowired
    private CylinderArrangementRepository cylinderArrangementRepository;

    @Override
    public Iterable<CylinderArrangement> findAll() {
        return cylinderArrangementRepository.findAll();
    }
}
