package com.interview.entity;

import com.interview.common.CloudCover;
import com.interview.common.WindDirection;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;

import static jakarta.persistence.EnumType.STRING;

/**
 * A representation of a sensor metrics data from sensors.
 */

@Entity
@Getter
@Setter
@ToString
@Table(name = "WEATHER_METRIC", indexes = {
        @Index(name = "IDX_EVENT_TIMESTAMP", columnList = "EVENT_TIMESTAMP")
})
public class WeatherMetric extends BaseEntity<Long> implements Serializable {
    @Column(name = "EVENT_TIMESTAMP", nullable = false)
    private OffsetDateTime timestamp;
    @Column(name = "TEMPERATURE")
    private Integer temperature;
    @Column(name = "HUMIDITY")
    private Integer humidity;
    @Column(name = "WIND_SPEED")
    private Integer windSpeed;
    @Column(name = "WIND_DIRECTION")
    @Enumerated(STRING)
      private WindDirection windDirection;
    @Column(name = "CLOUD_COVER")
    @Enumerated(STRING)
    private CloudCover cloudCover;
    @ManyToOne
    @JoinColumn(name = "SENSOR_ID", nullable = false)
    private Sensor sensor;

    @Getter
    public static enum Parameter {
        ID("id", "id"),
        TIMESTAMP("timestamp", "timestamp"),
        TEMPERATURE("temperature", "temperature"),
        HUMIDITY("humidity", "humidity"),
        WIND_SPEED("windSpeed", "windSpeed"),
        WIND_DIRECTION("windDirection", "windDirection"),
        CLOUD_COVER("cloudCover", "cloudCover"),
        SENSOR_ID("sensor.id", "sensorId"),
        START("timestamp", "start"),
        END("timestamp", "end");

        private final String fieldName;
        private final String apiQueryParameterName;
        Parameter(String fieldName, String apiParamName) {
          this.fieldName = fieldName;
          this.apiQueryParameterName = apiParamName;
        }
        public static Parameter fromApiParameter(String apiParamName) {
            for (Parameter b : Parameter.values()) {
               if (b.apiQueryParameterName.equals(apiParamName)) {
                 return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + apiParamName + "'");
        }
    }
}

