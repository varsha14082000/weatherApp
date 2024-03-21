package com.dice.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastSummaryResponse {
    private String address;
    private String timeZone;
    private String description;
    private List<WeatherForeCastForDay> weatherForeCastForDays;
    private WeatherForecastSummaryResponse.CurrentConditions currentCondition;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherForeCastForDay {
        private String datetime;
        private long datetimeEpoch;
        private double tempMax;
        private double tempMin;
        private double temp;
        private double feelsLikeMax;
        private double feelsLikeMin;
        private double feelsLike;
        private double humidity;
        private double precipition;
        private double pressure;
        private double visibility;
        private String sunrise;
        private long sunriseEpoch;
        private String sunset;
        private long sunsetEpoch;
        private String description;
        private List<WeatherForeCastForHour> weatherForeCastForHours;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherForeCastForHour {
        private String datetime;
        private long datetimeEpoch;
        private double temp;
        private double feelsLike;
        private double humidity;
        private double precipitation;
        private double pressure;
        private double visibility;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentConditions {
        private String datetime;
        private long datetimeEpoch;
        private double temp;
        private double feelsLike;
        private double humidity;
        private double precipitation;
        private double pressure;
        private double visibility;
        private String sunrise;
        private long sunriseEpoch;
        private String sunset;
        private long sunsetEpoch;
    }
}
