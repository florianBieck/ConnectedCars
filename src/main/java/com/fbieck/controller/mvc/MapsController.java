package com.fbieck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapsController {

    @GetMapping(value = "/mapview")
    public String view(){ return "mapview.html"; }

    @GetMapping(value = "/routes")
    public String routes(){ return "routes.html"; }

    @GetMapping(value = "/places")
    public String places(){ return "places.html"; }
}
