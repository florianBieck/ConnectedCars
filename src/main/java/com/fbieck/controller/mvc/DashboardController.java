package com.fbieck.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DashboardController {

    @RequestMapping(path = "/dashboard")
    public String dashboard(){ return "dashboard.html"; }
}
