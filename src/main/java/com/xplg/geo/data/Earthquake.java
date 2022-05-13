package com.xplg.geo.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Earthquake {
    private Double magnitude;
    private String place;
    private LocalDate localDate;

    public void setProperties(Map<String, ?> map) {
        magnitude = ((Number) map.get("mag")).doubleValue();
        place = (String) map.get("place");
        localDate = Instant.ofEpochMilli(((Number) map.get("time")).longValue()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public String getPlace() {
        return place;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "magnitude=" + magnitude +
                ", place='" + place + '\'' +
                ", localDate=" + localDate +
                '}';
    }
}
