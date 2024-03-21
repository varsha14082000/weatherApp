package com.dice.weather.provider;

import com.dice.weather.response.VisualCrossingForeCastSummaryResponse;

import java.io.IOException;

public interface WeatherForeCastSummaryProvider {

    VisualCrossingForeCastSummaryResponse fetchForeCastSummary(String location) throws IOException;
}
