package com.fbieck.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtrip")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "iduser")
    private User user;

    @OneToOne
    @JoinColumn(name = "idroute")
    private MyRoute myRoute;

    @OneToOne
    @JoinColumn(name = "idcar")
    private Car car;

    @Column(name = "duration")
    private Double duration;

    @Column(name = "routelength")
    private Double routelength;

    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MyRoute getMyRoute() {
        return myRoute;
    }

    public void setMyRoute(MyRoute myRoute) {
        this.myRoute = myRoute;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Double getRoutelength() {
        return routelength;
    }

    public void setRoutelength(Double routelength) {
        this.routelength = routelength;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
