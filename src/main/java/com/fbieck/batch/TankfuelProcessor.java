package com.fbieck.batch;

import com.fbieck.entities.Car;
import org.springframework.batch.item.ItemProcessor;

public class TankfuelProcessor implements ItemProcessor<Car, Car> {
    @Override
    public Car process(Car car) throws Exception {
        //tank change?
        return car;
    }
}
