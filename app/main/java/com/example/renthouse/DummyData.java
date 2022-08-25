package com.example.renthouse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private int houseId;
    private String city;
    private String country;

    @Override
    public String toString() {
        return "DummyData{" +
                "houseId=" + houseId +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public DummyData() {
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHouseId() {
        return houseId;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public DummyData(int houseId, String city, String country) {
        this.houseId = houseId;
        this.city = city;
        this.country = country;
    }


}
