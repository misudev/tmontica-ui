package com.internship.tmontica_ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController {

    @GetMapping(value={"/menus/**", "/orders/**", "/user/**"})
    public String routing(){
        return "/index.html";
    }

}
