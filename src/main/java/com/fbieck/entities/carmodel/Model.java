package com.fbieck.entities.carmodel;

import javax.persistence.*;

@Entity
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcarmodel")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "idbranch")
    private Branch branch;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "idmotor")
    private Motor motor;

    @Column(name = "countdoors")
    private Integer countdoors;

    @Column(name = "volumetank")
    private Integer volumetank;

    @OneToOne
    @JoinColumn(name = "idtransmission")
    private Transmission transmission;

    @OneToOne
    @JoinColumn(name = "idwheeldrive")
    private WheelDrive wheelDrive;

    @OneToOne
    @JoinColumn(name = "idemissionclass")
    private EmissionClass emissionClass;

    @OneToOne
    @JoinColumn(name = "idenergyefficiencyclass")
    private EnergyEfficiencyClass energyEfficiencyClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Integer getCountdoors() {
        return countdoors;
    }

    public void setCountdoors(Integer countdoors) {
        this.countdoors = countdoors;
    }

    public Integer getVolumetank() {
        return volumetank;
    }

    public void setVolumetank(Integer volumetank) {
        this.volumetank = volumetank;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public WheelDrive getWheelDrive() {
        return wheelDrive;
    }

    public void setWheelDrive(WheelDrive wheelDrive) {
        this.wheelDrive = wheelDrive;
    }

    public EmissionClass getEmissionClass() {
        return emissionClass;
    }

    public void setEmissionClass(EmissionClass emissionClass) {
        this.emissionClass = emissionClass;
    }

    public EnergyEfficiencyClass getEnergyEfficiencyClass() {
        return energyEfficiencyClass;
    }

    public void setEnergyEfficiencyClass(EnergyEfficiencyClass energyEfficiencyClass) {
        this.energyEfficiencyClass = energyEfficiencyClass;
    }
}
