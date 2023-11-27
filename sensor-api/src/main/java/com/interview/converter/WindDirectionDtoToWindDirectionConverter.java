package com.interview.converter;

import com.interview.api.model.WindDirectionDto;
import com.interview.common.WindDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WindDirectionDtoToWindDirectionConverter implements Converter<WindDirectionDto, WindDirection> {
    @Override
    public WindDirection convert(WindDirectionDto source) {
        return WindDirection.valueOf(source.name());
    }
}
