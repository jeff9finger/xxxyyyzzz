package com.interview.api.controller;

import com.interview.api.model.*;
import com.interview.service.SensorService;
import com.interview.service.WeatherMetricService;
import com.interview.service.WeatherStatisticService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <b>Sensors Endpoints</b>
 * <br>
 */
@RestController
@RequiredArgsConstructor
public class SensorsController implements SensorsApi {
    private final NativeWebRequest request;
    private final SensorService sensorService;
    private final WeatherMetricService weatherMetricService;
    private final WeatherStatisticService weatherStatisticService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<SensorDto> getSensor(Long id) {
        return ResponseEntity.ok(sensorService.getSensorById(id));
    }

    @Override
    public ResponseEntity<SensorDto> addSensor(@Valid SensorDto sensorDto) {
        SensorDto sensor = sensorService.createSensor(sensorDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(sensor.getId())
                .toUri();
        return ResponseEntity.created(uri).body(sensor);
    }

    @Override
    public ResponseEntity<WeatherMetricDto> importWeatherMetricsBySensorId(Long id, @Valid WeatherMetricDto weatherMetricDto) {
        WeatherMetricDto weatherMetric = weatherMetricService.createWeatherMetric(weatherMetricDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(weatherMetric.getId())
                .toUri();
        return ResponseEntity.created(uri).body(weatherMetric);
    }

    @Override
    public ResponseEntity<List<SensorDto>> querySensors(@Valid Long id, @Valid String name) {
        Map<String, Serializable> parameters = new HashMap<>();
        if (id != null) {
            parameters.put("id", id);
        }
        if (name != null) {
            parameters.put("name", name);
        }
        return ResponseEntity.ok(sensorService.querySensors(parameters));
    }

    @Override
    public ResponseEntity<List<WeatherMetricDto>> queryWeatherMetricsBySensorId(Long id,
                                                                                @Valid Integer temperature,
                                                                                @Min(0L) @Max(100L) @Valid Integer humidity,
                                                                                @Min(0L) @Valid Integer windSpeed,
                                                                                @Valid WindDirectionDto windDirection,
                                                                                @Valid CloudCoverDto cloudCover,
                                                                                @Valid OffsetDateTime timestamp,
                                                                                @Valid OffsetDateTime start,
                                                                                @Valid OffsetDateTime end) {
        Map<String,Serializable> parameters = new HashMap<>();
        parameters.put("sensorId", id);
        if (temperature != null) {
            parameters.put("temperature", temperature);
        }
        if (humidity != null) {
            parameters.put("humidity", humidity);
        }
        if (windSpeed != null) {
            parameters.put("windSpeed", windSpeed);
        }
        if (windDirection != null) {
            parameters.put("windDirection", windDirection);
        }
        if (cloudCover != null) {
            parameters.put("cloudCover", cloudCover);
        }
        if (start != null) {
            parameters.put("timestamp", timestamp);
        }
        if (start != null) {
            parameters.put("start", start);
        }
        if (end != null) {
            parameters.put("end", end);
        }
        return ResponseEntity.ok(weatherMetricService.queryWeatherMetricsBySensorId(parameters));
    }

    @Override
    public ResponseEntity<WeatherStatisticDto> queryWeatherStatisticBySensorId(Long id,
                                                                               @NotNull @Valid WeatherStatisticTypeDto statisticType,
                                                                               @Valid Integer temperature,
                                                                               @Min(0L) @Max(100L) @Valid Integer humidity,
                                                                               @Min(0L) @Valid Integer windSpeed,
                                                                               @Valid WindDirectionDto windDirection,
                                                                               @Valid CloudCoverDto cloudCover,
                                                                               @Valid OffsetDateTime start,
                                                                               @Valid OffsetDateTime end) {
        Map<String,Serializable> parameters = new HashMap<>();
        parameters.put("sensorId", id);
        if (temperature != null) {
            parameters.put("temperature", temperature);
        }
        if (humidity != null) {
            parameters.put("humidity", humidity);
        }
        if (windSpeed != null) {
            parameters.put("windSpeed", windSpeed);
        }
        if (windDirection != null) {
            parameters.put("windDirection", windDirection);
        }
        if (cloudCover != null) {
            parameters.put("cloudCover", cloudCover);
        }
        if (start != null) {
            parameters.put("start", start);
        }
        if (end != null) {
            parameters.put("end", end);
        }
        return ResponseEntity.ok(weatherStatisticService.queryWeatherStatisticsBySensorId(parameters, statisticType));
    }
}
