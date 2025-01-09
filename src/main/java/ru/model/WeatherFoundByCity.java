package ru.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class WeatherFoundByCity {
    public final String name;
    public final String shortCountry;
    public final int degrees;
    public final int humidity;
    public final String skyDefenition;
    public final String feelsLike;

}
