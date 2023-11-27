package com.interview.service;

import com.interview.api.model.WeatherStatisticDto;
import com.interview.api.model.WeatherStatisticTypeDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Map;

public interface WeatherStatisticService {
    WeatherStatisticDto queryWeatherStatisticsBySensorId(@NotNull Map<String, Serializable> parameters,
                                                         @NotNull WeatherStatisticTypeDto statisticType);
}
