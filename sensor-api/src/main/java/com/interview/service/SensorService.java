package com.interview.service;

import com.interview.api.model.SensorDto;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface SensorService {
    List<SensorDto> querySensors(@NotNull Map<String, Serializable> parameters);
    SensorDto createSensor(@NotNull SensorDto sensorDto);
    SensorDto getSensorById(@NotNull Long id);
}
