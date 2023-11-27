package com.interview.converter;

import com.interview.api.model.WindDirectionDto;
import com.interview.common.WindDirection;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class WindDirectionToWindDirectionDtoConverter implements Converter<WindDirection, WindDirectionDto> {
    @Override
    public WindDirectionDto convert(WindDirection source) {
        return WindDirectionDto.valueOf(source.name());
    }
}
