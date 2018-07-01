package com.fbieck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarsController {

    @GetMapping(value = "/mycars")
    private String mycars(){ return "mycars.html"; }
}
