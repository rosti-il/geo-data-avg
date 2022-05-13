package com.xplg.geo.data;

public class EarthquakesSummary {
    private final Double averageMagnitude;
    private final String highestLocation;

    public EarthquakesSummary(Double averageMagnitude, String highestLocation) {
        this.averageMagnitude = averageMagnitude;
        this.highestLocation = highestLocation;
    }

    @Override
    public String toString() {
        return String.format("%.2f, location of highest: %s", averageMagnitude, highestLocation);
    }
}
