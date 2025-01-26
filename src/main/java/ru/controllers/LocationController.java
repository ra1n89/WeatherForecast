package ru.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
    @Controller
    public class LocationController {

        @PostMapping("/addLocation")
        public String addLocation(
                @RequestParam String name,
                @RequestParam String country,
                @RequestParam String state,
                @RequestParam double lon,
                @RequestParam double lat) {

            // Логика для сохранения данных (например, в базу данных)
            System.out.println("Добавлена локация:");
            System.out.println("Name: " + name);
            System.out.println("Country: " + country);
            System.out.println("State: " + state);
            System.out.println("Lon: " + lon);
            System.out.println("Lat: " + lat);

            // Перенаправление на другую страницу
            return "redirect:/"; // Например, на страницу с сообщением об успехе
        }
    }

