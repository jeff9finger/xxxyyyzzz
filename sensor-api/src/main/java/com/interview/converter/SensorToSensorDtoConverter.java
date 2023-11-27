package com.interview.converter;

import com.interview.api.model.SensorDto;
import com.interview.entity.Sensor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SensorToSensorDtoConverter implements Converter<Sensor, SensorDto> {
    @Override
    public SensorDto convert(Sensor source) {
        SensorDto dto = new SensorDto();
        dto.setName(source.getName());
        dto.setId(source.getId());
        return dto;
    }
}
