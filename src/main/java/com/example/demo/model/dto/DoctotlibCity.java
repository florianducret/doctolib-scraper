package com.example.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctotlibCity {

    private String city;

    @JsonProperty(value = "place_id")
    private String placeId;

    public DoctotlibCity(String city, String placeId) {
        this.city = city;
        this.placeId = placeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}
