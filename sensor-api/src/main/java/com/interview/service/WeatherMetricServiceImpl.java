package com.interview.service;

import com.interview.api.model.WeatherMetricDto;
import com.interview.converter.WeatherMetricDtoToWeatherMetricConverter;
import com.interview.converter.WeatherMetricToWeatherMetricDtoConverter;
import com.interview.entity.WeatherMetric;
import com.interview.repository.WeatherMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WeatherMetricServiceImpl implements WeatherMetricService {
    private final WeatherMetricRepository weatherMetricRepository;
    private final WeatherMetricDtoToWeatherMetricConverter weatherMetricDtoToWeatherMetricConverter;
    private final WeatherMetricToWeatherMetricDtoConverter weatherMetricToWeatherMetricDtoConverter;
    @Override
    public List<WeatherMetricDto> queryWeatherMetrics(Map<String, Serializable> parameters) {
        final Map<WeatherMetric.Parameter,Serializable> repoParameters = new HashMap<>();
        WeatherMetricService.validateOrInitializeDateParameters(parameters)
                .forEach((key, value) -> repoParameters.put(WeatherMetric.Parameter.fromApiParameter(key), value));
        return weatherMetricRepository.findAllByParameters(repoParameters)
               .stream()
               .map(weatherMetricToWeatherMetricDtoConverter::convert)
               .collect(Collectors.toList());
    }

    @Override
    public List<WeatherMetricDto> queryWeatherMetricsBySensorId(Map<String, Serializable> parameters) {
        final Map<WeatherMetric.Parameter,Serializable> repoParameters = new HashMap<>();
        WeatherMetricService.validateOrInitializeDateParameters(parameters)
                .forEach((key, value) -> repoParameters.put(WeatherMetric.Parameter.fromApiParameter(key), value));
        return weatherMetricRepository.findAllByParameters(repoParameters)
               .stream()
               .map(weatherMetricToWeatherMetricDtoConverter::convert)
               .collect(Collectors.toList());
    }

    @Override
    public WeatherMetricDto createWeatherMetric(WeatherMetricDto weatherMetricDto) {
        if (weatherMetricDto.getTimestamp() == null) {
            weatherMetricDto.setTimestamp(OffsetDateTime.now());
        }
        return weatherMetricToWeatherMetricDtoConverter.convert(
                weatherMetricRepository.save(
                        Objects.requireNonNull(
                                weatherMetricDtoToWeatherMetricConverter.convert(weatherMetricDto))));
    }

    @Override
    public WeatherMetricDto getWeatherMetricById(Long id) {
        return weatherMetricToWeatherMetricDtoConverter.convert(
                weatherMetricRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("WeatherMetric not found")));
    }
}
