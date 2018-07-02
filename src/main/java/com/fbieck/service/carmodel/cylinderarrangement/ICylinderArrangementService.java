package com.fbieck.service.carmodel.cylinderarrangement;

import com.fbieck.entities.carmodel.CylinderArrangement;

public interface ICylinderArrangementService {

    Iterable<CylinderArrangement> findAll();
}
