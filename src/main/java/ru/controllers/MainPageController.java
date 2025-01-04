package ru.controllers;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @Autowired
    Flyway flyway;

    @GetMapping("/")
    String mainPage(){
        flyway.migrate();
        return "index";
    }
}
