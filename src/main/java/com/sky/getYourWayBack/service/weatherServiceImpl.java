package com.sky.getYourWayBack.service;

import com.google.gson.JsonArray;
import com.sky.getYourWayBack.data.entity.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.json.simple.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

@Service
public class weatherServiceImpl implements WeatherService {


    public String getWeather(String location) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + location + "&appid=f34536cfb20e6a85cadc9fadd3671867&units=metric");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                System.out.println("Error: " + responseCode);
            } else {
                Scanner sc = new Scanner(url.openStream());
                StringBuilder sb = new StringBuilder();
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();
                String jsonString = sb.toString();
                JSONObject json = new JSONObject(jsonString);
                System.out.println("JSON STRING: " + jsonString);

                double temperature = json.getJSONObject("main").getDouble("temp");
                int clouds = json.getJSONObject("clouds").getInt("all");
                System.out.println(json);
                JSONArray weatherArray = json.getJSONArray("weather");
                System.out.println("weather Array: " + weatherArray);
                Object weatherObject = weatherArray.get(0);
                System.out.println("weather object: " + weatherObject);
//                String cloundOrSun = (JSONObject)weatherArray.get(0).getAsJsonObject();
//
//                Map<String, String> weather = (Map<String, String>) weatherObject;
//                String main = weather.get("main");
//                System.out.println(main);


//                System.out.println(sunniness.getString("main"));
//                JSONObject cloudiness = new JSONObject(weatherObject);
//                System.out.println("cloudiness: " + cloudiness);
//                String cloudOutput = cloudiness.getString("main");
//                System.out.println(clouds);

                org.json.simple.JSONObject dataReturn = new org.json.simple.JSONObject();
                dataReturn.put("Temperature", temperature);
                dataReturn.put("Weather", weatherObject);
                return dataReturn.toString();
//                return new Weather(temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

