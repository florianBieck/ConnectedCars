package com.fbieck.entities.carmodel;

import javax.persistence.*;

@Entity
public class Motor {

    @Id
    @Column(name = "idmotor")
    private String id;

    @OneToOne
    @JoinColumn(name = "idtype")
    private MotorType motorType;

    @Column(name = "countcylinder")
    private Integer countcylinder;

    @OneToOne
    @JoinColumn(name = "idcylinderarrangement")
    private CylinderArrangement cylinderArrangement;

    @Column(name = "cubiccapacity")
    private Integer cubiccapcity; // cm³

    @Column(name = "kilowatt")
    private Float kilowatt;

    @Column(name = "torque")
    private Float torque;

    @Column(name = "acceleration")
    private Float acceleration; //0 to 100 km/h

    @Column(name = "highspeed")
    private Float highspeed; //km/h

    @Column(name = "co2")
    private Float co2; // g/km

    @Column(name = "consumption")
    private Float consumption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MotorType getMotorType() {
        return motorType;
    }

    public void setMotorType(MotorType motorType) {
        this.motorType = motorType;
    }

    public Integer getCountcylinder() {
        return countcylinder;
    }

    public void setCountcylinder(Integer countcylinder) {
        this.countcylinder = countcylinder;
    }

    public CylinderArrangement getCylinderArrangement() {
        return cylinderArrangement;
    }

    public void setCylinderArrangement(CylinderArrangement cylinderArrangement) {
        this.cylinderArrangement = cylinderArrangement;
    }

    public Integer getCubiccapcity() {
        return cubiccapcity;
    }

    public void setCubiccapcity(Integer cubiccapcity) {
        this.cubiccapcity = cubiccapcity;
    }

    public Float getKilowatt() {
        return kilowatt;
    }

    public void setKilowatt(Float kilowatt) {
        this.kilowatt = kilowatt;
    }

    public Float getTorque() {
        return torque;
    }

    public void setTorque(Float torque) {
        this.torque = torque;
    }

    public Float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Float acceleration) {
        this.acceleration = acceleration;
    }

    public Float getHighspeed() {
        return highspeed;
    }

    public void setHighspeed(Float highspeed) {
        this.highspeed = highspeed;
    }

    public Float getCo2() {
        return co2;
    }

    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    public Float getConsumption() {
        return consumption;
    }

    public void setConsumption(Float consumption) {
        this.consumption = consumption;
    }
}
