package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.service.car.ICarService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class GeolocationReader implements ItemReader<Car> {

    @Autowired
    private ICarService carService;

    private int counter = 0;

    @Override
    public Car read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        ArrayList<Car> cars = (ArrayList) carService.findAll();
        counter = counter >= cars.size() - 1 ? 0 : counter;
        if (cars.size() == 0) {
            return null;
        }
        Car output = cars.get(counter);
        counter++;
        return output;
    }
}
