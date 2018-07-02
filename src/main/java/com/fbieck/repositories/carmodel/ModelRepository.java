package com.fbieck.repositories.carmodel;

import com.fbieck.entities.carmodel.Model;
import org.springframework.data.repository.CrudRepository;

public interface ModelRepository extends CrudRepository<Model, Integer> {
}
