package com.fbieck.batch;

import com.fbieck.entities.Car;
import org.springframework.batch.item.ItemProcessor;

import java.util.concurrent.ThreadLocalRandom;

public class SignalstrengthProcessor implements ItemProcessor<Car, Car> {

    @Override
    public Car process(Car car) throws Exception {
        int oldsignal = car.getSignalstrength();
        int propabillity_change = ThreadLocalRandom.current().nextInt(0, 5000);
        if (propabillity_change == 0) {
            int propabillity_newsignal = ThreadLocalRandom.current().nextInt(0, 1000);
            if (propabillity_newsignal >= 0 && propabillity_newsignal < 50) {
                car.setSignalstrength(0);
            } else if (propabillity_newsignal >= 50 && propabillity_newsignal < 150) {
                car.setSignalstrength(1);
            } else if (propabillity_newsignal >= 150 && propabillity_newsignal < 300) {
                car.setSignalstrength(2);
            } else if (propabillity_newsignal >= 300 && propabillity_newsignal < 600) {
                car.setSignalstrength(3);
            } else if (propabillity_newsignal >= 600 && propabillity_newsignal < 1000) {
                car.setSignalstrength(4);
            }
            System.out.println("Signalchange [" + car.getId() + "]: " + oldsignal + " -> " + car.getSignalstrength());
        }
        return car;
    }
}
