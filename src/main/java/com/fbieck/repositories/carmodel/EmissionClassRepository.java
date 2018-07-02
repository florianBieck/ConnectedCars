package com.fbieck.repositories.carmodel;

import com.fbieck.entities.carmodel.EmissionClass;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Entity;

@Entity
public interface EmissionClassRepository extends CrudRepository<EmissionClass, String> {
}
