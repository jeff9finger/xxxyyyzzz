package com.interview.converter;

import com.interview.api.model.WeatherStatisticDto;
import com.interview.entity.WeatherStatistic;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherStatisticToWeatherStatisticDtoConverter implements Converter<WeatherStatistic, WeatherStatisticDto> {
    @Override
    public WeatherStatisticDto convert(WeatherStatistic source) {
        return new WeatherStatisticDto()
                .temperature(source.getTemperatureStatistic())
                .humidity(source.getHumidityStatistic())
                .windSpeed(source.getWindSpeedStatistic());
    }
}
