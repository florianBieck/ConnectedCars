package com.fbieck.entities;

import javax.persistence.*;

@Entity
public class MyRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmyroute")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "iduser")
    private User user;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "start")
    private MyPlace start;

    @OneToOne
    @JoinColumn(name = "end")
    private MyPlace end;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public MyPlace getStart() {
        return start;
    }

    public void setStart(MyPlace start) {
        this.start = start;
    }

    public MyPlace getEnd() {
        return end;
    }

    public void setEnd(MyPlace end) {
        this.end = end;
    }

}
