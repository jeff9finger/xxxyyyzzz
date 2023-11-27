# Weather Sensor API

A small application that shows some basic endpoints for managing weather sensors and the data produced by them. Specifically,
the application receives weather data from various sensors that report metrics - `temperature`, `humidity`, `wind_speed`,
`wind_direction`, and `cloud_cover`.

## Basic Features 
- Receives new metric values as the weather changes around the sensor - `POST /sensors/{id/weather-metrics`.
- Provides the capability to query the sensor metric data - `GET /sensors/{id/weather-metrics` and `GET /weather-metrics`
- Provides the capability to query summary data over a set of data - `GET /sensors/{id/weather-statistics`
  - `sum`
  - `min`
  - `max`
  - `average`

### Implementation Notes
A  `GET /weather-statistics` endpoint is not currently available. This endpoint would allow a summaary of aggregate data
across multiple sensors.

# API Documentation for Sensor API

<a name="documentation-for-api-endpoints"></a>
## Documentation for API Endpoints

All URIs are relative to *http://localhost*

| Class | Method | HTTP request | Description |
|------------ | ------------- | ------------- | -------------|
| *SensorApi* | [**addSensor**](Apis/SensorApi.md#addsensor) | **POST** /sensors | Add a new sensor |
*SensorApi* | [**getSensor**](Apis/SensorApi.md#getsensor) | **GET** /sensors/{id} | Get sensor details |
*SensorApi* | [**querySensors**](Apis/SensorApi.md#querysensors) | **GET** /sensors | Query all sensors |
| *WeatherMetricApi* | [**importWeatherMetrics**](Apis/WeatherMetricApi.md#importweathermetrics) | **POST** /weather-metrics | Add weather metrics |
*WeatherMetricApi* | [**importWeatherMetricsBySensorId**](Apis/WeatherMetricApi.md#importweathermetricsbysensorid) | **POST** /sensors/{id}/weather-metrics | Add metrics from the sensor. |
*WeatherMetricApi* | [**queryWeatherMetrics**](Apis/WeatherMetricApi.md#queryweathermetrics) | **GET** /weather-metrics | Query the metrics previously persisted from the specified sensor. |
*WeatherMetricApi* | [**queryWeatherMetricsBySensorId**](Apis/WeatherMetricApi.md#queryweathermetricsbysensorid) | **GET** /sensors/{id}/weather-metrics | Query the metrics previously persisted from the specified sensor. |
| *WeatherStatisticApi* | [**queryWeatherStatisticBySensorId**](Apis/WeatherStatisticApi.md#queryweatherstatisticbysensorid) | **GET** /sensors/{id}/weather-statistics | Query the weather metrics previously persisted from the specified sensor |


<a name="documentation-for-models"></a>
## Documentation for Models

 - [CloudCover](./Models/CloudCover.md)
 - [ConversionType](./Models/ConversionType.md)
 - [Entity](./Models/Entity.md)
 - [Sensor](./Models/Sensor.md)
 - [WeatherMetric](./Models/WeatherMetric.md)
 - [WeatherStatistic](./Models/WeatherStatistic.md)
 - [WeatherStatisticType](./Models/WeatherStatisticType.md)
 - [WindDirection](./Models/WindDirection.md)


<a name="documentation-for-authorization"></a>
## Documentation for Authorization

All endpoints do not require authorization.
