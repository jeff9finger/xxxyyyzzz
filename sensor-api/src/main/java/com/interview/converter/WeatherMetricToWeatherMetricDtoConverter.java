package com.interview.converter;

import com.interview.api.model.WeatherMetricDto;
import com.interview.entity.WeatherMetric;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherMetricToWeatherMetricDtoConverter implements Converter<WeatherMetric, WeatherMetricDto> {
    private final CloudCoverToCloudCoverDtoConverter cloudCoverToCloudCoverDtoConverter;
    private final WindDirectionToWindDirectionDtoConverter windDirectionToWindDirectionDtoConverter;

    @Override
    public WeatherMetricDto convert(WeatherMetric source) {
        WeatherMetricDto dto = new WeatherMetricDto()
                .sensorId(source.getSensor().getId())
                .temperature(source.getTemperature())
                .humidity(source.getHumidity())
                .cloudCover(cloudCoverToCloudCoverDtoConverter.convert(source.getCloudCover()))
                .timestamp(source.getTimestamp())
                .windDirection(windDirectionToWindDirectionDtoConverter.convert(source.getWindDirection()))
                .windSpeed(source.getWindSpeed());
        dto.setId(source.getId());
        return dto;
    }
}
