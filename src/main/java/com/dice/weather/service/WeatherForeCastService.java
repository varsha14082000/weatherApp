package com.dice.weather.service;

import com.dice.weather.handler.WeatherForeCaseOperations;
import com.dice.weather.response.WeatherForecastSummaryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WeatherForeCastService {

    private final WeatherForeCaseOperations weatherForeCaseOperations;
    @Autowired
    public WeatherForeCastService(WeatherForeCaseOperations weatherForeCaseOperations) {
        this.weatherForeCaseOperations = weatherForeCaseOperations;
    }

    public WeatherForecastSummaryResponse fetchForeCastSummaryByLocation(String location) throws IOException {
       return weatherForeCaseOperations.fetchForeCastSummary(location);
    }

    public WeatherForecastSummaryResponse fetchHourlyForeCastSummaryByLocation(String location) throws IOException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String todayDate = today.format(formatter);
        WeatherForecastSummaryResponse weatherForecastSummaryResponse = weatherForeCaseOperations.fetchForeCastSummary(location);
        List<WeatherForecastSummaryResponse.WeatherForeCastForDay> weatherForeCastForDayList  = new ArrayList<>();
        weatherForecastSummaryResponse.getWeatherForeCastForDays().forEach(weatherForeCastForDay -> {
            if(todayDate.equals(weatherForeCastForDay.getDatetime())) {
                weatherForeCastForDayList.add(weatherForeCastForDay);
            }
        });
        weatherForecastSummaryResponse.setWeatherForeCastForDays(weatherForeCastForDayList);

        return weatherForecastSummaryResponse;

    }



}
