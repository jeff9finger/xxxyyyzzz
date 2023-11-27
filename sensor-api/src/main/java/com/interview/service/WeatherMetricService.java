package com.interview.service;

import com.interview.api.model.WeatherMetricDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public interface WeatherMetricService {
    List<WeatherMetricDto> queryWeatherMetrics(@NotNull Map<String,Serializable> parameters);
    List<WeatherMetricDto> queryWeatherMetricsBySensorId(@NotNull Map<String, Serializable> parameters);
    WeatherMetricDto createWeatherMetric(@NotNull WeatherMetricDto WeatherMetricDto);
    WeatherMetricDto getWeatherMetricById(@NotNull Long id);

    /*
        Ensures that date range is no more than one month.
        If no dates are provided, add default values of start = now() - 1 month, end = now()
        Note: A month is assumed to be 30 days
    */
    static Map<String, Serializable> validateOrInitializeDateParameters(@NotNull Map<String, Serializable> parameters) {
        OffsetDateTime end = (OffsetDateTime) parameters.getOrDefault("end", OffsetDateTime.now());
        OffsetDateTime start = (OffsetDateTime) parameters.getOrDefault("start", end.minus(Duration.ofDays(30)));
        Duration between = Duration.between(start, end);
        final long monthMillis = Duration.ofDays(30).toMillis();;
        if (between.toMillis() > monthMillis) {
            throw new IllegalArgumentException("The range of 'start' and 'end' can not span more than a month");
        }
        parameters.putIfAbsent("end", end);
        parameters.putIfAbsent("start", start);
        return parameters;
    }

}
