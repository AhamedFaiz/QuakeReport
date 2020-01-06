package com.example.quakereport;

import java.net.URL;

public class Earthquake {
    private double magnitude;
    private  Long date;
    private String place;
    private String url;

    public String getUrl() {
        return url;
    }

    public Earthquake(double magnitude, Long date, String place, String url) {
        this.magnitude = magnitude;
        this.date = date;
        this.place = place;
        this.url = url;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public Long getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }
}
