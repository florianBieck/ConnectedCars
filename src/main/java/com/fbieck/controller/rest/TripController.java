package com.fbieck.controller.rest;

import com.fbieck.auth.FFUserPrincipal;
import com.fbieck.entities.Trip;
import com.fbieck.service.trip.ITripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class TripController {

    @Autowired
    private ITripService tripService;

    @RequestMapping(value = "/trip/all")
    private Iterable<Trip> findAll() {
        return tripService.findAll();
    }

    @RequestMapping(value = "/trip/bysession")
    private Iterable<Trip> findAllByUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            return tripService.findAllByUser(
                    ((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser()
            );
        }
        return null;
    }

    @RequestMapping(value = "/trip/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    private Trip createTrip(
            @RequestBody MultiValueMap<String, String> formData,
            @RequestParam(value = "timestamp")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date timestamp
    ) {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && formData.containsKey("idroute")
                && formData.containsKey("idcar")
                && formData.containsKey("duration")) {
            return tripService.createTrip(
                    ((FFUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser(),
                    Integer.parseInt(formData.getFirst("idroute")),
                    Integer.parseInt(formData.getFirst("idcar")),
                    Double.parseDouble(formData.getFirst("duration")),
                    timestamp
            );
        }
        return null;
    }
}
