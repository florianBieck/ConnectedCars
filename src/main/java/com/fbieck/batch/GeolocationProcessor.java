package com.fbieck.batch;

import com.fbieck.entities.Car;
import com.fbieck.util.GeolocationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.util.concurrent.ThreadLocalRandom;

public class GeolocationProcessor implements ItemProcessor<Car, Car> {

    private static final Logger logger = LoggerFactory.getLogger(GeolocationProcessor.class);

    @Override
    public Car process(Car car) throws Exception {
        double lat2 = car.getLatitude() + ThreadLocalRandom.current().nextDouble(-0.0001, 0.0001);
        double long2 = car.getLongitude() + ThreadLocalRandom.current().nextDouble(-0.0001, 0.0001);
        car.setSpeed(GeolocationUtil.getDistance(car.getLatitude(), car.getLongitude(), lat2, long2) * 3.6);
        /*logger.info("Processing Geolocation ("+car.getTitle()+" ["+car.getId()+"]): "+car.getLatitude()+":"+car.getLongitude()+" "
            +lat2+":"+long2+" -> "+car.getSpeed());*/
        car.setLatitude(lat2);
        car.setLongitude(long2);
        return car;
    }
}
