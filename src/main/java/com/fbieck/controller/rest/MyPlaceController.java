package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.entities.MyPlace;
import com.fbieck.service.myplace.IMyPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPlaceController {

    @Autowired
    private IMyPlaceService myPlaceService;

    @RequestMapping(value = "/place/all")
    private Iterable<MyPlace> findAll(){
        return myPlaceService.findAll();
    }

    @RequestMapping(value = "/place/bysession")
    private Iterable<MyPlace> findAllBySession() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return myPlaceService.findAllByUser(
                    ((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
            );
        }
        return null;
    }

    @RequestMapping(value = "/place/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private MyPlace createPlace(
            @RequestBody MultiValueMap<String, String> formData
    ) {
        if (formData.containsKey("latitude")
                && formData.containsKey("longitude")
                && formData.containsKey("title")) {
            return myPlaceService.createMyPlace(((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser(),
                    Double.parseDouble(formData.getFirst("latitude")),
                    Double.parseDouble(formData.getFirst("longitude")),
                    formData.getFirst("title")
            );
        }
        return null;
    }
}
