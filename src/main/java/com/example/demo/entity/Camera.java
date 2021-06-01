package com.example.demo.entity;

import com.opencsv.bean.CsvBindByPosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Camera {

    public static final Pattern NUMBER_PATTERN = Pattern.compile("CM-([0-9]+)");

    private int number;

    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    private Double latitude;

    @CsvBindByPosition(position = 2)
    private Double longitude;

    public Camera() {
    }

    public Camera(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Camera setName(String name) {
        this.name = name;

        Matcher matcher = NUMBER_PATTERN.matcher(name);
        if (matcher.find()) {
            this.number = Integer.parseInt(matcher.group(1));
        }

        return this;
    }

    public Camera setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Camera setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getName() {
        return name;
    }

    public int getNumber()
    {
        return number;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
