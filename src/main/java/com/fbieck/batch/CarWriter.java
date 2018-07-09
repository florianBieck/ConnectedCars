package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.service.car.ICarService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarWriter implements ItemWriter<Car> {

    @Autowired
    private ICarService carService;

    @Override
    public void write(List<? extends Car> list) throws Exception {
        list.forEach(car -> carService.save(car));
    }
}
