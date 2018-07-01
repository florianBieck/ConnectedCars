package com.fbieck.service.user;

import com.fbieck.entities.User;
import com.fbieck.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(String name, String surname, String email, String password,
                           Date birthday, Boolean is_man) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setPasswordhash(passwordEncoder().encode(password));
        user.setEmail(email);
        user.setEnabled(false);
        return userRepository.save(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public User findById(Integer iduser) {
        return userRepository.findById(iduser).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Boolean enableUser(User user) {
        if ( user != null ){
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(String name, String surname, String email, String password) {
        User user = findByEmail(email);
        if (user != null && passwordEncoder().matches(password, user.getPasswordhash())){
            user.setEmail(email);
            user.setName(name);
            user.setSurname(surname);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public User updatePassword(User user, String newpassword) {
        if (user != null){
            user.setPasswordhash(passwordEncoder().encode(newpassword));
            return userRepository.save(user);
        }
        return null;
    }

}