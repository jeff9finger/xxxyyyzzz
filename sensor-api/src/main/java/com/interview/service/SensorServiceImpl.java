package com.interview.service;

import com.interview.api.model.SensorDto;
import com.interview.converter.SensorDtoToSensorConverter;
import com.interview.converter.SensorToSensorDtoConverter;
import com.interview.entity.Sensor;
import com.interview.repository.SensorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class is an implementation of the SensorService interface.
 * It provides methods for querying sensors, creating a new sensor, and getting a sensor by ID.
 */
@Component
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository sensorRepository;
    private final SensorToSensorDtoConverter sensorToSensorDtoConverter;
    private final SensorDtoToSensorConverter sensorDtoToSensorConverter;

    @Override
    public List<SensorDto> querySensors(Map<String, Serializable> parameters) {
        final Map<Sensor.Parameter, Serializable> repoParameters = new HashMap<>();
        parameters.forEach((key, value) -> repoParameters.put(Sensor.Parameter.fromApiParameter(key), value));
        return sensorRepository.findAllByParameters(repoParameters)
                .stream()
                .map(sensorToSensorDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public SensorDto createSensor(SensorDto sensorDto) {
        Sensor sensor = sensorRepository.save(Objects.requireNonNull(sensorDtoToSensorConverter.convert(sensorDto)));
        return sensorToSensorDtoConverter.convert(sensor);
    }

    @Override
    public SensorDto getSensorById(Long id) {
        Optional<Sensor> sensor = sensorRepository.findById(id);
        return sensorToSensorDtoConverter
                .convert(sensorRepository.findById(id).orElseThrow(
                        () -> new RuntimeException("Sensor not found")));
    }
}
