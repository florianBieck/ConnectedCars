package com.fbieck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MenuController {

    @RequestMapping(path = "/")
    public String menu() { return "menu.html"; }
}
