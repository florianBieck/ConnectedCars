package com.fbieck.repositories.carmodel;

import com.fbieck.entities.carmodel.Motor;
import org.springframework.data.repository.CrudRepository;

public interface MotorRepository extends CrudRepository<Motor, String> {
}
