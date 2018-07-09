package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.service.car.ICarService;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarReader implements ItemReader<Car>, ItemStream {

    @Autowired
    private ICarService carService;

    private List<Car> cars;

    @Override
    public Car read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!cars.isEmpty()) {
            return cars.remove(0);
        }
        return null;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        cars = (List<Car>) carService.findAll();
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {

    }

    @Override
    public void close() throws ItemStreamException {
        cars.clear();
    }
}
