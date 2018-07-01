package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.entities.MyRoute;
import com.fbieck.service.myroute.IMyRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRouteController {

    @Autowired
    private IMyRouteService myRouteService;

    @RequestMapping(value = "/route/all")
    private Iterable<MyRoute> findAll(){
        return myRouteService.findAll();
    }

    @RequestMapping(value = "/route/bysession")
    private Iterable<MyRoute> findAllBySession(){
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return myRouteService.findAllByUser(
                    ((FFUserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
            );
        }
        return null;
    }
}
