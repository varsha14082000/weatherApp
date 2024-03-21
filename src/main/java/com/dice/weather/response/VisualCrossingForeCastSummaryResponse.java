package com.dice.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisualCrossingForeCastSummaryResponse {
    private double queryCost;
    private double latitude;
    private double longitude;
    private String resolvedAddress;
    private String address;
    private String timezone;
    private double tzoffset;
    private String description;
    private List<Day> days;
    private List<Object> alerts;
    private Map<String, Station> stations;
    private CurrentConditions currentConditions;

    private String refId;
    private boolean success;
    private String message;
    private String errorMessage;
    private String switchpeReferenceId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Day {
        private String datetime;
        private long datetimeEpoch;
        private double tempmax;
        private double tempmin;
        private double temp;
        private double feelslikemax;
        private double feelslikemin;
        private double feelslike;
        private double dew;
        private double humidity;
        private double precip;
        private double precipprob;
        private double precipcover;
        private Object preciptype;
        private double snow;
        private double snowdepth;
        private double windgust;
        private double windspeed;
        private double winddir;
        private double pressure;
        private double cloudcover;
        private double visibility;
        private double solarradiation;
        private double solarenergy;
        private double uvindex;
        private double severerisk;
        private String sunrise;
        private long sunriseEpoch;
        private String sunset;
        private long sunsetEpoch;
        private double moonphase;
        private String conditions;
        private String description;
        private String icon;
        private List<String> stations;
        private String source;
        private List<Hour> hours;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Hour {
        private String datetime;
        private long datetimeEpoch;
        private double temp;
        private double feelslike;
        private double humidity;
        private double dew;
        private double precip;
        private double precipprob;
        private double snow;
        private double snowdepth;
        private Object preciptype;
        private double windgust;
        private double windspeed;
        private double winddir;
        private double pressure;
        private double visibility;
        private double cloudcover;
        private double solarradiation;
        private double solarenergy;
        private double uvindex;
        private double severerisk;
        private String conditions;
        private String icon;
        private List<String> stations;
        private String source;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Station {
        private double distance;
        private double latitude;
        private double longitude;
        private double useCount;
        private String id;
        private String name;
        private double quality;
        private double contribution;
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
        private double feelslike;
        private double humidity;
        private double dew;
        private Object precip;
        private double precipprob;
        private double snow;
        private double snowdepth;
        private Object preciptype;
        private Object windgust;
        private double windspeed;
        private double winddir;
        private double pressure;
        private double visibility;
        private double cloudcover;
        private double solarradiation;
        private double solarenergy;
        private double uvindex;
        private String conditions;
        private String icon;
        private List<String> stations;
        private String source;
        private String sunrise;
        private long sunriseEpoch;
        private String sunset;
        private long sunsetEpoch;
        private double moonphase;
    }

}