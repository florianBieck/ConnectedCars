package com.fbieck.dto;

import com.fbieck.entities.User;

public class UserDetails {

    private String email, name, surname;

    public UserDetails(User user) {
        email = user.getEmail();
        name = user.getName();
        surname = user.getSurname();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
