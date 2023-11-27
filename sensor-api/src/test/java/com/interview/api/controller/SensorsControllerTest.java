package com.interview.api.controller;

import com.interview.api.model.SensorDto;
import com.interview.service.SensorService;
import com.interview.service.WeatherMetricService;
import com.interview.service.WeatherStatisticService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SensorsControllerTest {
    @Mock
    private NativeWebRequest request;

    @Mock
    private SensorService sensorService;

    @Mock
    private WeatherMetricService weatherMetricService;

    @Mock
    private WeatherStatisticService weatherStatisticService;

    @InjectMocks
    private SensorsController sensorsController;

    private SensorDto sensorDto;

    @BeforeEach
    public void setup() {
        sensorDto = new SensorDto();
        sensorDto.setId(1L);
        sensorDto.setName("ACME Test Sensor");
    }

    @Test
    public void shouldReturnSensorById() {
        when(sensorService.getSensorById(any(Long.class))).thenReturn(sensorDto);

        ResponseEntity<SensorDto> response = sensorsController.getSensor(1L);

        assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertEquals(response.getBody(), sensorDto);
    }

    // Add more tests for the other controller methods
}
