package com.fbieck.entities;

import com.fbieck.entities.carmodel.Model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Car {

    @Id
    @Column(name = "idcar")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "locationtimestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @Column(name = "mileage")
    private Double mileage;

    @Column(name = "speed")
    private Double speed;

    @OneToOne
    @JoinColumn(name = "idcarmodel")
    private Model model;

    @OneToOne
    @JoinColumn(name = "iduser")
    private User user;

    @Column(name = "direction")
    private Float direction;

    @Column(name = "revolutions")
    private Double revolutions;

    @Column(name = "oiltemp")
    private Float oiltemp;

    @Column(name = "watertemp")
    private Float watertemp;

    @Column(name = "consumption")
    private Float consumption;

    @Column(name = "consumptionmean")
    private Float consumptionmean;

    @Column(name = "speedmean")
    private Float speedmean;

    @Column(name = "fuel")
    private Integer fuel;

    @Column(name = "signalstrength")
    private Integer signalstrength;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getMileage() {
        return mileage;
    }

    public void setMileage(Double mileage) {
        this.mileage = mileage;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Float getDirection() {
        return direction;
    }

    public void setDirection(Float direction) {
        this.direction = direction;
    }

    public Double getRevolutions() {
        return revolutions;
    }

    public void setRevolutions(Double revolutions) {
        this.revolutions = revolutions;
    }

    public Float getOiltemp() {
        return oiltemp;
    }

    public void setOiltemp(Float oiltemp) {
        this.oiltemp = oiltemp;
    }

    public Float getWatertemp() {
        return watertemp;
    }

    public void setWatertemp(Float watertemp) {
        this.watertemp = watertemp;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }

    public Float getConsumptionmean() {
        return consumptionmean;
    }

    public void setConsumptionmean(Float consumptionmean) {
        this.consumptionmean = consumptionmean;
    }

    public Float getSpeedmean() {
        return speedmean;
    }

    public void setSpeedmean(Float speedmean) {
        this.speedmean = speedmean;
    }

    public Integer getFuel() {
        return fuel;
    }

    public void setFuel(Integer fuel) {
        this.fuel = fuel;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getSignalstrength() {
        return signalstrength;
    }

    public void setSignalstrength(Integer signalstrength) {
        this.signalstrength = signalstrength;
    }
}
