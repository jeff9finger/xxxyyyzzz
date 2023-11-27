package com.interview.service;

import com.interview.api.model.WeatherStatisticDto;
import com.interview.api.model.WeatherStatisticTypeDto;
import com.interview.common.WeatherStatisticType;
import com.interview.converter.WeatherStatisticToWeatherStatisticDtoConverter;
import com.interview.entity.WeatherStatistic;
import com.interview.repository.WeatherMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WeatherStatisticServiceImpl implements WeatherStatisticService {
    private final WeatherMetricRepository weatherMetricRepository;
    private final WeatherStatisticToWeatherStatisticDtoConverter weatherStatisticToWeatherStatisticDtoConverter;

    /**
     * Query and perform the requested function to obtain the desired statistic
     * @param parameters parameters from the controller
     * @param statisticType The requested statistic type
     * @return The object representing the specified statistic
     */
    @Override
    public WeatherStatisticDto queryWeatherStatisticsBySensorId(Map<String, Serializable> parameters, WeatherStatisticTypeDto statisticType) {
        final Map<WeatherStatistic.Parameter,Serializable> repoParameters = new HashMap<>();
       WeatherMetricService.validateOrInitializeDateParameters(parameters)
                .forEach((key, value) -> repoParameters.put(WeatherStatistic.Parameter.fromApiParameter(key), value));
        WeatherStatistic weatherStatistic = weatherMetricRepository.queryStatistics(WeatherStatisticType.valueOf(statisticType.name()), repoParameters);
        return Objects.requireNonNull(weatherStatisticToWeatherStatisticDtoConverter.convert(weatherStatistic)).statisticType(statisticType);
    }
}
