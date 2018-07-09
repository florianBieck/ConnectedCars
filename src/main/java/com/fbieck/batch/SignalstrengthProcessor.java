package com.fbieck.batch;

import com.fbieck.entities.Car;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class SignalstrengthProcessor implements ItemProcessor<Car, Car> {

    @Override
    public Car process(Car car) throws Exception {
        int p_change = ThreadLocalRandom.current().nextInt(0, 5000);
        if (p_change == 0) {

            int p_newsignal = ThreadLocalRandom.current().nextInt(0, 1000);
            if (p_newsignal >= 0 && p_newsignal < 50) {
                car.setSignalstrength(0);
            } else if (p_newsignal >= 50 && p_newsignal < 150) {
                car.setSignalstrength(1);
            } else if (p_newsignal >= 150 && p_newsignal < 300) {
                car.setSignalstrength(2);
            } else if (p_newsignal >= 300 && p_newsignal < 600) {
                car.setSignalstrength(3);
            } else if (p_newsignal >= 600 && p_newsignal < 1000) {
                car.setSignalstrength(4);
            }

        }
        return car;
    }
}
