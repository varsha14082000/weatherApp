package com.dice.weather.mapper;

import com.dice.weather.response.VisualCrossingForeCastSummaryResponse;
import com.dice.weather.response.WeatherForecastSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface WeatherForeCastSummaryMapper{
    WeatherForeCastSummaryMapper INSTANCE = Mappers.getMapper(WeatherForeCastSummaryMapper.class);

    default WeatherForecastSummaryResponse mapToDTO(VisualCrossingForeCastSummaryResponse visualCrossingForeCastSummaryResponse) {

        WeatherForecastSummaryResponse weatherForecastSummaryResponse = new WeatherForecastSummaryResponse();
        List<WeatherForecastSummaryResponse.WeatherForeCastForDay> weatherForeCastForDays =  new ArrayList<>();
        WeatherForecastSummaryResponse.CurrentConditions currentCondition = null;
        if(!ObjectUtils.isEmpty(visualCrossingForeCastSummaryResponse)) {
            if(!StringUtils.isEmpty(visualCrossingForeCastSummaryResponse.getAddress())) {
                weatherForecastSummaryResponse.setAddress(visualCrossingForeCastSummaryResponse.getAddress());
            }
            if(!StringUtils.isEmpty(visualCrossingForeCastSummaryResponse.getTimezone())) {
                weatherForecastSummaryResponse.setTimeZone(visualCrossingForeCastSummaryResponse.getTimezone());
            }
            if(!StringUtils.isEmpty(visualCrossingForeCastSummaryResponse.getDescription())) {
                weatherForecastSummaryResponse.setDescription(visualCrossingForeCastSummaryResponse.getDescription());
            }

            VisualCrossingForeCastSummaryResponse.CurrentConditions presentCondition =  visualCrossingForeCastSummaryResponse.getCurrentConditions();


            if(!ObjectUtils.isEmpty(visualCrossingForeCastSummaryResponse.getCurrentConditions())) {
                currentCondition = WeatherForecastSummaryResponse.CurrentConditions.builder()
                        .datetime(presentCondition.getDatetime())
                        .datetimeEpoch(presentCondition.getDatetimeEpoch())
                        .temp(presentCondition.getTemp())
                        .feelsLike(presentCondition.getFeelslike())
                        .humidity(presentCondition.getHumidity())
                        .pressure(presentCondition.getPressure())
                        .visibility(presentCondition.getVisibility())
                        .sunrise(presentCondition.getSunrise())
                        .sunriseEpoch(presentCondition.getSunriseEpoch())
                        .sunset(presentCondition.getSunset())
                        .sunsetEpoch(presentCondition.getSunsetEpoch())
                        .build();
            }

            if(!CollectionUtils.isEmpty(visualCrossingForeCastSummaryResponse.getDays())) {
                visualCrossingForeCastSummaryResponse.getDays().forEach(day -> {
                    List<WeatherForecastSummaryResponse.WeatherForeCastForHour> weatherForeCastForHours = new ArrayList<>();
                    if(!CollectionUtils.isEmpty(day.getHours())) {
                        day.getHours().forEach(hour -> {
                            weatherForeCastForHours.add(WeatherForecastSummaryResponse.WeatherForeCastForHour.builder()
                                    .datetime(hour.getDatetime())
                                    .datetimeEpoch(hour.getDatetimeEpoch())
                                    .temp(hour.getTemp())
                                    .feelsLike(hour.getFeelslike())
                                    .humidity(hour.getHumidity())
                                    .precipitation(hour.getPrecip())
                                    .pressure(hour.getPressure())
                                    .visibility(hour.getVisibility())
                                    .build());
                        });
                    }
                    weatherForeCastForDays.add(WeatherForecastSummaryResponse.WeatherForeCastForDay.builder()
                            .datetime(day.getDatetime())
                            .datetimeEpoch(day.getDatetimeEpoch())
                            .tempMax(day.getTempmax())
                            .tempMin(day.getTempmin())
                            .temp(day.getTemp())
                            .feelsLike(day.getFeelslike())
                            .feelsLikeMin(day.getFeelslikemin())
                            .feelsLikeMax(day.getFeelslikemax())
                            .humidity(day.getHumidity())
                            .precipition(day.getPrecip())
                            .pressure(day.getPressure())
                            .visibility(day.getVisibility())
                            .sunrise(day.getSunrise())
                            .sunset(day.getSunset())
                            .sunriseEpoch(day.getSunriseEpoch())
                            .sunsetEpoch(day.getSunsetEpoch())
                            .description(day.getDescription())
                            .weatherForeCastForHours(weatherForeCastForHours)
                            .build()
                    );
                });
            }
        }

        return   WeatherForecastSummaryResponse.builder()
                .address(visualCrossingForeCastSummaryResponse.getAddress())
                .timeZone(visualCrossingForeCastSummaryResponse.getTimezone())
                .description(visualCrossingForeCastSummaryResponse.getDescription())
                .currentCondition(currentCondition)
                .weatherForeCastForDays(weatherForeCastForDays)
                .build();
    }


}
