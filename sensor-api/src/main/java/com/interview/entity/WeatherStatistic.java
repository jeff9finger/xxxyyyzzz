package com.interview.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherStatistic {
    // Only used to hold the computed statistic values
    private Double temperatureStatistic;
    private Double humidityStatistic;
    private Double windSpeedStatistic;
    public WeatherStatistic(Double tempStat, Double humidityStat, Double windSpeedStat) {
        this.temperatureStatistic = tempStat;
        this.humidityStatistic = humidityStat;
        this.windSpeedStatistic = windSpeedStat;
    }
    @Getter
    public static enum Parameter {
        TEMPERATURE("temperature", "temperature"),
        TEMPERATURE_STATISTIC("temperatureStatistic", "temperature"),
        HUMIDITY("humidity", "humidity"),
        HUMIDITY_STATISTIC("humidityStatistic", "humidity"),
        WIND_SPEED("windSpeed", "windSpeed"),
        WIND_SPEED_STATISTIC("windSpeedStatistic", "windSpeed"),
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
        public static WeatherStatistic.Parameter fromApiParameter(String apiParamName) {
            for (WeatherStatistic.Parameter b : WeatherStatistic.Parameter.values()) {
               if (b.apiQueryParameterName.equals(apiParamName)) {
                 return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + apiParamName + "'");
        }
    }
}
