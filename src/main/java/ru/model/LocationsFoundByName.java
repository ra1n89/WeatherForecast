package ru.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LocationsFoundByName {
    public final String name;
    public final String shortCountry;
    public final String state;
    public final double lon;
    public final double lat;
}
