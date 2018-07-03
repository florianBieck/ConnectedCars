package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.service.car.ICarService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GeolocationWriter implements ItemWriter<Car> {

    @Autowired
    private ICarService carService;

    @Override
    public void write(List<? extends Car> list) throws Exception {
        list.forEach(car -> carService.save(car));
    }
}
