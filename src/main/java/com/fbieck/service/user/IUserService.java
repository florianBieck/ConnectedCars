package com.fbieck.service.user;

import com.fbieck.entities.User;

import java.util.Date;

public interface IUserService {

    Iterable<User> findAll();

    User createUser(String name, String surname, String email, String password, Date birthday, Boolean is_man);

    User findById(Integer iduser);

    User findByEmail(String email);

    Boolean enableUser(User user);

    User updateUser(String name, String surname, String email, String password);

    User updatePassword(User user, String newpassword);
}