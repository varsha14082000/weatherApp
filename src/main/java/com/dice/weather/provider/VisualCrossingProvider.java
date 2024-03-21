package com.dice.weather.provider;

import com.dice.weather.VisualCrossingClient;
import com.dice.weather.response.VisualCrossingForeCastSummaryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("visualCrossing")
public class VisualCrossingProvider implements WeatherForeCastSummaryProvider {

    @Autowired
    public final VisualCrossingClient visualCrossingClient;

    public VisualCrossingProvider(VisualCrossingClient visualCrossingClient) {
        this.visualCrossingClient = visualCrossingClient;
    }

    @Override
    public VisualCrossingForeCastSummaryResponse fetchForeCastSummary(String location) throws IOException {
        return visualCrossingClient.getWeatherForeCast(location);
    }

}
