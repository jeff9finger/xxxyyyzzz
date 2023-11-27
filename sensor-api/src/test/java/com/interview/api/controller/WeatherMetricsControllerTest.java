package com.interview.api.controller;

import com.interview.api.model.CloudCoverDto;
import com.interview.api.model.WeatherMetricDto;
import com.interview.api.model.WindDirectionDto;
import com.interview.service.WeatherMetricService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherMetricsControllerTest {

    @Mock
    private NativeWebRequest request;

    @Mock
    private WeatherMetricService weatherMetricService;

    @InjectMocks
    private WeatherMetricsController weatherMetricsController;

    private WeatherMetricDto weatherMetricDto;
    private List<WeatherMetricDto> weatherMetricDtoList;

    @BeforeEach
    public void setup() {
        weatherMetricDto = new WeatherMetricDto();
        weatherMetricDto.setId(1L);
        weatherMetricDto.setSensorId(1L);
        weatherMetricDto.setTimestamp(OffsetDateTime.now());
        weatherMetricDto.setTemperature(25);
        weatherMetricDto.setHumidity(48);
        weatherMetricDto.setWindSpeed(5);
        weatherMetricDto.setCloudCover(CloudCoverDto.CLEAR);
        weatherMetricDto.setWindDirection(WindDirectionDto.S);

        weatherMetricDtoList = new ArrayList<>();
        weatherMetricDtoList.add(weatherMetricDto);
    }

    @Test
    public void shouldReturnWeatherMetricsList() {
        when(weatherMetricService.queryWeatherMetrics(any(Map.class))).thenReturn(weatherMetricDtoList);

        ResponseEntity<List<WeatherMetricDto>> response = weatherMetricsController.queryWeatherMetrics(1L, 1L, null, null, null, null, null, null, null, null);
        assertEquals(response.getBody().size(), 1);
        assertEquals(response.getBody().get(0).getId(), weatherMetricDto.getId());

        verify(weatherMetricService, times(1)).queryWeatherMetrics(any(Map.class));
    }

    @Test
    @Disabled
    public void shouldCreateWeatherMetric() {
        when(weatherMetricService.createWeatherMetric(any(WeatherMetricDto.class))).thenReturn(weatherMetricDto);

        ResponseEntity<WeatherMetricDto> response = weatherMetricsController.importWeatherMetrics(weatherMetricDto);
        assertEquals(response.getBody().getId(), weatherMetricDto.getId());

        //verify(weatherMetricService, times(1)).createWeatherMetric(any(WeatherMetricDto.class));
    }

    // Add more tests for more scenarios as per your specifications
}
