package ru.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import ru.model.WeatherFoundByCity;

@UtilityClass
public class JsonUtil {

    static public WeatherFoundByCity stringToJson(String jsonString) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        String name = jsonNode.get("name").asText();
        String shortCountry = jsonNode.get("sys").get("country").asText();
        ;
        int degrees = jsonNode.get("main").get("temp").asInt();
        ;
        int humidity = jsonNode.get("main").get("humidity").asInt();
        ;
        String skyDefenition = jsonNode.get("weather").asText("main");
        ;
        String feelsLike = jsonNode.get("main").get("feels_like").asText();

        return new WeatherFoundByCity(name, shortCountry, degrees, humidity, skyDefenition, feelsLike);
    }
}
