package com.saurabh.journal.apiresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
@Data
public class WeatherResponse {
    public Location location;
    public Current current;
    @Data
    public static class AirQuality{
        private String co;
        private String no2;
        private String o3;
        private String so2;
        private String pm2_5;
        private String pm10;
        @JsonProperty("us-epa-index")
        private String usEpaIndex;
        @JsonProperty("gb-defra-index;")
        private String gbDefraIndex;
    }
    @Data
    public static class Astro{
        private String sunrise;
        private String sunset;
        private String moonrise;
        private String moonset;
        private String moon_phase;
        private int moon_illumination;
    }
    @Data
    public static class Current{
        @JsonProperty("observation_time")
        private String observationTime;
        private int temperature;
        @JsonProperty("weather_code")
        private int weatherCode;
        @JsonProperty("weather_icons")
        private ArrayList<String> weatherIcons;
        @JsonProperty("weather_descriptions")
        private ArrayList<String> weatherDescriptions;
        private Astro astro;
        @JsonProperty("air_quality")
        private AirQuality airQuality;
        @JsonProperty("wind_speed")
        private int windSpeed;
        @JsonProperty("wind_dir")
        private String windDir;
        private int pressure;
        private int precip;
        private int humidity;
        private int cloudcover;
        private int feelslike;
        @JsonProperty("uv_index")
        private String uvIndex;
        private int visibility;
        @JsonProperty("is_day")
        private String isDay;
    }
    @Data
    public static class Location{
        private String name;
        private String country;
        private String region;
        private String lat;
        private String lon;
        @JsonProperty("timezone_id")
        private String timezoneId;
        private String localtime;
        @JsonProperty("localtime_epoch")
        private int localtimeEpoch;
        @JsonProperty("utc_offset")
        private String utcOffset;
    }
}
