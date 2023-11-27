package com.interview.converter;

import com.interview.api.model.CloudCoverDto;
import com.interview.common.CloudCover;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CloudCoverDtoToCloudCoverConverter implements Converter<CloudCoverDto, CloudCover> {
    @Override
    public CloudCover convert(CloudCoverDto source) {
        return CloudCover.valueOf(source.name());
    }
}
