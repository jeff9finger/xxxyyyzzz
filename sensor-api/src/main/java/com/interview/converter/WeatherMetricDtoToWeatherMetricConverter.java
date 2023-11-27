package com.interview.converter;

import com.interview.api.model.WeatherMetricDto;
import com.interview.entity.Sensor;
import com.interview.entity.WeatherMetric;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherMetricDtoToWeatherMetricConverter implements Converter<WeatherMetricDto, WeatherMetric> {
    private final CloudCoverDtoToCloudCoverConverter cloudCoverDtoToCloudCoverConverter;
    private final WindDirectionDtoToWindDirectionConverter windDirectionDtoToWindDirectionConverter;

    @Override
    public WeatherMetric convert(WeatherMetricDto source) {
        WeatherMetric entity = new WeatherMetric();
        entity.setId(source.getId());
        Sensor sensor = new Sensor();
        sensor.setId(source.getSensorId());
        entity.setSensor(sensor);
        entity.setHumidity(source.getHumidity());
        entity.setTimestamp(source.getTimestamp());
        entity.setTemperature(source.getTemperature());
        entity.setCloudCover(cloudCoverDtoToCloudCoverConverter.convert(source.getCloudCover()));
        entity.setWindSpeed(source.getWindSpeed());
        entity.setWindDirection(windDirectionDtoToWindDirectionConverter.convert(source.getWindDirection()));
        return entity;
    }
}
