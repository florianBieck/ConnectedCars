package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import com.fbieck.service.myplace.IMyPlaceService;
import com.fbieck.service.myroute.IMyRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MyRouteController {

    @Autowired
    private IMyRouteService myRouteService;

    @Autowired
    private IMyPlaceService myPlaceService;

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

    @RequestMapping(value = "/route/create", params = {"startlat, startlong, starttitle, endlat, endlong, endtitle, title"})
    private MyRoute create(
            @RequestParam(value = "startlat") Double startlat,
            @RequestParam(value = "startlong") Double startlong,
            @RequestParam(value = "starttitle") String starttitle,
            @RequestParam(value = "endlat") Double endlat,
            @RequestParam(value = "endlong") Double endlong,
            @RequestParam(value = "endtitle") String endtitle,
            @RequestParam(value = "title") String title
    ) {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            User user = ((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            return myRouteService.createMyRoute(
                    user,
                    myPlaceService.createMyPlace(user, startlat, startlong, starttitle),
                    myPlaceService.createMyPlace(user, endlat, endlong, endtitle),
                    title,
                    new Date()
            );
        }
        return null;
    }
}
