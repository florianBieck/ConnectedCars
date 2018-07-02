package com.fbieck.entities;

import com.fbieck.entities.carmodel.Model;

import javax.persistence.*;

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

    @Column(name = "mileage")
    private Double mileage;

    @OneToOne
    @JoinColumn(name = "idcarmodel")
    private Model model;

    @OneToOne
    @JoinColumn(name = "iduser")
    private User user;

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
}
