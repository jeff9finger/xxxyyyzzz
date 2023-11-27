package com.interview.converter;

import com.interview.api.model.SensorDto;
import com.interview.entity.Sensor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SensorDtoToSensorConverter implements Converter<SensorDto, Sensor> {
    @Override
    public Sensor convert(SensorDto source) {
        Sensor entity = new Sensor();
        entity.setId(source.getId());
        entity.setName(source.getName());
        return entity;
    }
}
