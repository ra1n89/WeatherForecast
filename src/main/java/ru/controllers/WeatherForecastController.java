package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class WeatherForecastController {

    @GetMapping("forecast")
    String forecast() throws IOException, InterruptedException {

        //System.out.println(stringHttpResponse.body());

        return "forecast";
    }
}
