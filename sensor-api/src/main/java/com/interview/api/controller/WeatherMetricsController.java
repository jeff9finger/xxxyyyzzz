package com.interview.api.controller;

import com.interview.api.model.CloudCoverDto;
import com.interview.api.model.WeatherMetricDto;
import com.interview.api.model.WindDirectionDto;
import com.interview.service.WeatherMetricService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
 * <b>SensorMetrics Endpoints</b>
 * <br>
 */
@RestController
@RequiredArgsConstructor
public class WeatherMetricsController implements WeatherMetricsApi {
    private final NativeWebRequest request;
    private final WeatherMetricService weatherMetricService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<List<WeatherMetricDto>> queryWeatherMetrics(@Valid Long id,
                                                                      @Min(1L) @Valid Long sensorId,
                                                                      @Valid Integer temperature,
                                                                      @Min(0L) @Max(100L) @Valid Integer humidity,
                                                                      @Min(0L) @Valid Integer windSpeed,
                                                                      @Valid WindDirectionDto windDirection,
                                                                      @Valid CloudCoverDto cloudCover,
                                                                      @Valid OffsetDateTime timestamp,
                                                                      @Valid OffsetDateTime start,
                                                                      @Valid OffsetDateTime end) {
        Map<String, Serializable> parameters = new HashMap<>();
        if (id != null) {
            parameters.put("id", id);
        }
        if (sensorId != null) {
            parameters.put("sensorId", sensorId);
        }
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
        if (timestamp != null) {
            parameters.put("timestamp", timestamp);
        }
        if (start != null) {
            parameters.put("start", start);
        }
        if (end != null) {
            parameters.put("end", end);
        }

        return ResponseEntity.ok(weatherMetricService.queryWeatherMetrics(parameters));
    }

    @Override
    public ResponseEntity<WeatherMetricDto> importWeatherMetrics(@Valid WeatherMetricDto weatherMetricDto) {
        if (weatherMetricDto.getSensorId() == null) {
            throw new RuntimeException("'sensor_id' is required when importing data using this endpoint.");
        }
        WeatherMetricDto weatherMetric = weatherMetricService.createWeatherMetric(weatherMetricDto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(weatherMetric.getId())
                .toUri();
        return ResponseEntity.created(uri).body(weatherMetric);
    }
}

