package ru.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import ru.model.LocationsFoundByName;
import ru.model.WeatherFoundByCity;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class JsonUtil {
    public static WeatherFoundByCity stringToJson(String jsonString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        String name = jsonNode.get("name").asText();
        String shortCountry = jsonNode.get("sys").get("country").asText();
        int degrees = jsonNode.get("main").get("temp").asInt();
        int humidity = jsonNode.get("main").get("humidity").asInt();
        String skyDefenition = jsonNode.get("weather").asText("main");
        String feelsLike = jsonNode.get("main").get("feels_like").asText();
        return new WeatherFoundByCity(name, shortCountry, degrees, humidity, skyDefenition, feelsLike);
    }
    public static List<LocationsFoundByName>  locationsStringToJson(String jsonString) throws JsonProcessingException {
        List<LocationsFoundByName> locationsFoundByNameList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode cityNode = objectMapper.readTree(jsonString);

        for(JsonNode node: cityNode) {
            String name = node.get("name").asText();
            String shortCountry = node.get("country").asText();
            String state = node.has("state") ? node.get("state").asText() : "N/A";
            double lon = node.get("lon").asDouble();
            double lat = node.get("lat").asDouble();
           locationsFoundByNameList.add(new LocationsFoundByName(name, shortCountry, state, lon, lat));
        }

        return locationsFoundByNameList;
    }
}
