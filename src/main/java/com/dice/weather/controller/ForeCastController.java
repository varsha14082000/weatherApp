package com.dice.weather.controller;
import com.dice.weather.authentication.HeaderAuthentication;
import com.dice.weather.response.WeatherForecastSummaryResponse;
import com.dice.weather.service.WeatherForeCastService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class ForeCastController {
    private WeatherForeCastService weatherForeCastService;
    private HeaderAuthentication headerAuthentication;

    @Autowired
    public ForeCastController(WeatherForeCastService weatherForeCastService, HeaderAuthentication headerAuthentication) {
        this.weatherForeCastService = weatherForeCastService;
        this.headerAuthentication = headerAuthentication;
    }

    @GetMapping("/forecast-summary/{location}")
    public WeatherForecastSummaryResponse fetchForeCastSummaryByLocation(@PathVariable("location")String location, HttpServletRequest request) throws Exception {
        try{
            headerAuthentication.authenticateClient(request);
            return weatherForeCastService.fetchForeCastSummaryByLocation(location);
        }
        catch(Exception ex){
            log.warn(String.valueOf(ex.getStackTrace()));
            throw new Exception(ex);
        }
    }

    @GetMapping("/forecast-summary/hourly/{location}")
    public WeatherForecastSummaryResponse fetchHourlyForeCastSummaryByLocation(@PathVariable("location") String location,HttpServletRequest request) throws Exception {
        try{
            headerAuthentication.authenticateClient(request);
            return weatherForeCastService.fetchHourlyForeCastSummaryByLocation(location);
        }
        catch(Exception ex){
            log.warn(String.valueOf(ex.getStackTrace()));
            throw new Exception(ex);
        }
    }









}
