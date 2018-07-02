package com.fbieck.entities.carmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EmissionClass {

    @Id
    @Column(name = "emissionclass")
    private String emissionclass;

    public String getEmissionclass() {
        return emissionclass;
    }

    public void setEmissionclass(String emissionclass) {
        this.emissionclass = emissionclass;
    }
}
