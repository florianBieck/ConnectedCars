package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.entities.MyRoute;
import com.fbieck.entities.User;
import com.fbieck.service.myplace.IMyPlaceService;
import com.fbieck.service.myroute.IMyRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/route/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private MyRoute create(
            @RequestBody MultiValueMap<String, String> formData
    ) {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && formData.containsKey("startlat")
                && formData.containsKey("startlong")
                && formData.containsKey("starttitle")
                && formData.containsKey("endlat")
                && formData.containsKey("endlong")
                && formData.containsKey("endtitle")
                && formData.containsKey("title")
                ) {
            User user = ((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            return myRouteService.createMyRoute(
                    user,
                    myPlaceService.createMyPlace(user,
                            Double.parseDouble(formData.getFirst("startlat")),
                            Double.parseDouble(formData.getFirst("startlong")),
                            formData.getFirst("starttitle")
                    ),
                    myPlaceService.createMyPlace(user,
                            Double.parseDouble(formData.getFirst("endlat")),
                            Double.parseDouble(formData.getFirst("endlong")),
                            formData.getFirst("endtitle")
                    ),
                    formData.getFirst("title")
            );
        }
        return null;
    }
}
