package com.example.renthouse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DummyDatatoJasonParser {

    public static List<DummyData> getObjectFromJason(String jason) {
        List<DummyData> data;
        try {
            JSONArray jsonArray = new JSONArray(jason);
            data = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) jsonArray.get(i);
                DummyData d = new DummyData();
                d.setHouseId(jsonObject.getInt("id"));
                d.setCity(jsonObject.getString("city"));
                d.setCountry(jsonObject.getString("Country"));
                data.add(d);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return data;
    }
}
