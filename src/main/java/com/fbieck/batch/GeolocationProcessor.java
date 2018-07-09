package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.util.GeolocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GeolocationProcessor implements ItemProcessor<Car, Car> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeolocationProcessor.class);

    private final double amount = 0.00001;

    @Override
    public Car process(Car car) throws Exception {
        if (car.getTimestamp() != null) {
            calculate(car);
        } else {
            car.setTimestamp(new Date());
        }
        return car;
    }

    private void calculate(Car car) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(new Date().getTime() - car.getTimestamp().getTime()));
        if (calendar.get(Calendar.MILLISECOND) != 0) {
            double lat2 = car.getLatitude() + getRandomAmount();
            double long2 = car.getLongitude() + getRandomAmount();
            double distance = GeolocationUtil.getDistance(car.getLatitude(), car.getLongitude(), lat2, long2); //distance in meters
            car.setMileage(car.getMileage() + distance);
            car.setSpeed(distance * (3.6 * calendar.get(Calendar.MILLISECOND) / 100));
            car.setLatitude(lat2);
            car.setLongitude(long2);
        }
    }

    private Double getRandomAmount() {
        return ThreadLocalRandom.current().nextDouble(amount * -1, amount);
    }
}
