package com.xplg.geo;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.xplg.geo.config.JacksonJsonPathConfiguration;
import com.xplg.geo.data.Earthquake;
import com.xplg.geo.data.EarthquakesSummary;

import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class MainClass {

    private static final String GEO_DATA_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.geojson";

    static {
        Configuration.setDefaults(JacksonJsonPathConfiguration.getInstance());
    }

    public static void main(String[] args) throws Exception {
        try (InputStream is = new URL(GEO_DATA_URL).openStream()) {
            Earthquake[] earthquakes = JsonPath.parse(is).read("$.features", Earthquake[].class);
            Arrays.sort(earthquakes, Comparator.comparing(Earthquake::getLocalDate));
            Arrays.stream(earthquakes)
                    .collect(Collectors.groupingBy(Earthquake::getLocalDate,
                            LinkedHashMap::new,
                            Collectors.collectingAndThen(Collectors.toList(), list -> {
                                OptionalDouble average = list.stream().mapToDouble(Earthquake::getMagnitude).average();
                                String highestLocation = list.stream().max(Comparator.comparing(Earthquake::getMagnitude)).map(Earthquake::getPlace).orElse(null);
                                return new EarthquakesSummary(average.isPresent() ? average.getAsDouble() : null, highestLocation);
                            })))
                    .forEach((localDate, earthquakesSummary) -> System.out.println(localDate + " " + earthquakesSummary));
        }
    }
}
