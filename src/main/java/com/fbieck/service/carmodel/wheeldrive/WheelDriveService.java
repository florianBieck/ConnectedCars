package com.fbieck.service.carmodel.wheeldrive;

import com.fbieck.entities.carmodel.WheelDrive;
import com.fbieck.repositories.carmodel.WheelDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WheelDriveService implements IWheelDriveService {

    @Autowired
    private WheelDriveRepository wheelDriveRepository;

    @Override
    public Iterable<WheelDrive> findAll() {
        return wheelDriveRepository.findAll();
    }
}
