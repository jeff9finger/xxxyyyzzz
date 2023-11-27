package com.interview.converter;

import com.interview.api.model.CloudCoverDto;
import com.interview.common.CloudCover;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CloudCoverToCloudCoverDtoConverter implements Converter<CloudCover, CloudCoverDto> {
    @Override
    public CloudCoverDto convert(CloudCover source) {
        return CloudCoverDto.valueOf(source.name());
    }
}
