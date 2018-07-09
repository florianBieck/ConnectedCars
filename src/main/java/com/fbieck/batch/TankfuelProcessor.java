package com.fbieck.batch;

import com.fbieck.entities.Car;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class TankfuelProcessor implements ItemProcessor<Car, Car> {

    @Override
    public Car process(Car car) throws Exception {
        int p_change = ThreadLocalRandom.current().nextInt(0, 10000);
        if (p_change == 0) {
            if (ThreadLocalRandom.current().nextInt(0, 50000) == 0) {
                car.setFuel(car.getModel().getVolumetank()); //refill
            } else {
                car.setFuel(car.getFuel() >= 1 ? car.getFuel() - 1 : 0); //consume
            }
        }
        return car;
    }
}
