package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.util.GeolocationUtil;
import org.springframework.batch.item.ItemProcessor;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class GeolocationProcessor implements ItemProcessor<Car, Car> {

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
            car.setSpeed(GeolocationUtil.getDistance(car.getLatitude(), car.getLongitude(), lat2, long2) * (3.6 * calendar.get(Calendar.MILLISECOND) / 100));
            car.setLatitude(lat2);
            car.setLongitude(long2);
        }
    }

    private Double getRandomAmount() {
        return ThreadLocalRandom.current().nextDouble(amount * -1, amount);
    }
}
