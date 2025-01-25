package ru.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.model.WeatherFoundByCity;
import ru.service.UserValidationService;
import ru.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Controller
public class MainPageController {

    @Autowired
    UserValidationService userValidationService;

    @GetMapping("/")
    String mainPage(HttpServletRequest request, HttpServletResponse response) {
        //TODO: перенести в сервлетЛистенер
        Cookie[] cookies = request.getCookies();

        if (cookies == null) {


        } else {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("GUID") && userValidationService.isUserAuthorized(cookie.getValue())) {

                }
            }
        }



        return "index";
    }

    @GetMapping("search-results")
    String searchResult(@RequestParam("search_filter") String searchLocationFilter,
                        Model model)
            throws IOException, InterruptedException {

        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder(
                        URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" +
                                searchLocationFilter + "&limit=5&appid=9aa0aba07c5c897aaf5cf0454766edb7"))
                .GET()
                .build();

        HttpResponse<String> stringHttpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        WeatherFoundByCity weatherFoundByCity = JsonUtil.stringToJson(stringHttpResponse.body());
        System.out.println(weatherFoundByCity);

        model.addAttribute("searchResults", stringHttpResponse.body());

        return "search-results";
    }
}
