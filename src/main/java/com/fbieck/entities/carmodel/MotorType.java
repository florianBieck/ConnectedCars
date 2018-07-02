package com.fbieck.entities.carmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MotorType {

    @Id
    @Column(name = "motortype")
    private String motortype;

    public String getMotortype() {
        return motortype;
    }

    public void setMotortype(String motortype) {
        this.motortype = motortype;
    }
}
