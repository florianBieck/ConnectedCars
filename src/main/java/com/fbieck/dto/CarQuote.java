package com.fbieck.dto;

import com.fbieck.entities.Car;

public class CarQuote {

    private Car car;

    private float percentage;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
