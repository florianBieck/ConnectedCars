package com.fbieck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TripsController {

    @RequestMapping(value = "/trips")
    private String trips() {
        return "trips.html";
    }
}
