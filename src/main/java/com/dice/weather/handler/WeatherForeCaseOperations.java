package com.dice.weather.handler;

import com.dice.weather.mapper.WeatherForeCastSummaryMapper;
import com.dice.weather.provider.VisualCrossingProvider;
import com.dice.weather.provider.WeatherForeCastSummaryProvider;
import com.dice.weather.response.VisualCrossingForeCastSummaryResponse;
import com.dice.weather.response.WeatherForecastSummaryResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WeatherForeCaseOperations {
    private final WeatherForeCastSummaryProvider weatherForeCastSummaryProvider;

    public WeatherForeCaseOperations(ApplicationContext context) {
        this.weatherForeCastSummaryProvider = context.getBean(VisualCrossingProvider.class);
    }


    public WeatherForecastSummaryResponse fetchForeCastSummary(String location) throws IOException {
        VisualCrossingForeCastSummaryResponse visualCrossingForeCastSummaryResponse = weatherForeCastSummaryProvider.fetchForeCastSummary(location);
        return WeatherForeCastSummaryMapper.INSTANCE.mapToDTO(visualCrossingForeCastSummaryResponse);
    }

}
