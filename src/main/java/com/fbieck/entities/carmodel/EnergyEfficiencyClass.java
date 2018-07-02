package com.fbieck.entities.carmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EnergyEfficiencyClass {

    @Id
    @Column(name = "efficiencyclass")
    private String efficiencyclass;

    public String getEfficiencyclass() {
        return efficiencyclass;
    }

    public void setEfficiencyclass(String efficiencyclass) {
        this.efficiencyclass = efficiencyclass;
    }
}
