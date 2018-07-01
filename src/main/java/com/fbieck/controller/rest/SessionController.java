package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.dto.UserDetails;
import com.fbieck.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping(value = "/session/all")
    private UserDetails all(){
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            User user = ((FFUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                    .getUser();
            return new UserDetails(user);
        }
        return null;
    }
}
