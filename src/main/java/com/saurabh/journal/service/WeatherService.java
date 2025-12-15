package com.saurabh.journal.service;

import com.saurabh.journal.apiresponse.WeatherResponse;
import com.saurabh.journal.cache.AppCache;
import com.saurabh.journal.constant.PlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
@Service
public class WeatherService {
    @Value("${weather.api.key}")
   private   String  apiKey;
    @Autowired
    private AppCache appCache;
    private static final String API="http://api.weatherstack.com/current?access_key=APIKEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
      WeatherResponse weatherResponse1=   redisService.getValue("weather_of_"+city,WeatherResponse.class);
        if(weatherResponse1!=null){
            return weatherResponse1;
        }else{
            String finalApi=appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolder.CITY,city).replace(PlaceHolder.API_KEY,apiKey);

//        String finalApi=API.replace("CITY",city).replace("APIKEY",apiKey);
            ResponseEntity<WeatherResponse> weatherResponseResponseEntity= restTemplate.exchange(finalApi, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse weatherResponse=weatherResponseResponseEntity.getBody();
            if(weatherResponse!=null){
                redisService.setValue("weather_of_"+city,weatherResponse,300l);
            }
            return weatherResponse;
        }

    }
}
