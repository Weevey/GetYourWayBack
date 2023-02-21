package com.qa.cardatabase.service;

import com.qa.cardatabase.data.entity.Weather;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class weatherServiceImpl implements WeatherService {


    public Weather getWeather(String location) {
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
                double temperature = json.getJSONObject("main").getDouble("temp");
                return new Weather(temperature);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

