package com.web.curse.controllers;

import org.example.controllers.HelloController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloControllerImpl implements HelloController {
    @GetMapping("/")
    public String getAccount(){
        return "index";
    }
}
