package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class WeatherForecastController {

    @GetMapping("forecast")
    String forecast() throws IOException, InterruptedException {

        //System.out.println(stringHttpResponse.body());

        return "forecast";
    }
}
