# WeatherMetricApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**importWeatherMetrics**](WeatherMetricApi.md#importWeatherMetrics) | **POST** /weather-metrics | Add weather metrics |
| [**importWeatherMetricsBySensorId**](WeatherMetricApi.md#importWeatherMetricsBySensorId) | **POST** /sensors/{id}/weather-metrics | Add metrics from the sensor. |
| [**queryWeatherMetrics**](WeatherMetricApi.md#queryWeatherMetrics) | **GET** /weather-metrics | Query the metrics previously persisted from the specified sensor. |
| [**queryWeatherMetricsBySensorId**](WeatherMetricApi.md#queryWeatherMetricsBySensorId) | **GET** /sensors/{id}/weather-metrics | Query the metrics previously persisted from the specified sensor. |


<a name="importWeatherMetrics"></a>
# **importWeatherMetrics**
> WeatherMetric importWeatherMetrics(WeatherMetric)

Add weather metrics

    The sensor data is added to the application.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **WeatherMetric** | [**WeatherMetric**](../Models/WeatherMetric.md)|  | |

### Return type

[**WeatherMetric**](../Models/WeatherMetric.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="importWeatherMetricsBySensorId"></a>
# **importWeatherMetricsBySensorId**
> WeatherMetric importWeatherMetricsBySensorId(id, WeatherMetric)

Add metrics from the sensor.

    The sensor data is added to the application.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor | [default to null] |
| **WeatherMetric** | [**WeatherMetric**](../Models/WeatherMetric.md)|  | |

### Return type

[**WeatherMetric**](../Models/WeatherMetric.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="queryWeatherMetrics"></a>
# **queryWeatherMetrics**
> List queryWeatherMetrics(id, sensor\_id, temperature, humidity, wind\_speed, wind\_direction, cloud\_cover, timestamp, start, end)

Query the metrics previously persisted from the specified sensor.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor weather metric | [optional] [default to null] |
| **sensor\_id** | **Long**| The id of the Weather Sensor | [optional] [default to null] |
| **temperature** | **Integer**| The temperature reported by the Weather Sensor | [optional] [default to null] |
| **humidity** | **Integer**| The humidity percentage reported by the Weather Sensor | [optional] [default to null] |
| **wind\_speed** | **Integer**| The wind speed reported by the Weather Sensor | [optional] [default to null] |
| **wind\_direction** | [**WindDirection**](../Models/.md)| The wind direction reported by the Weather Sensor | [optional] [default to null] [enum: N, NE, NNE, NW, NNW, S, SE, SSE, SW, SSW, ENE, ESE, W, WNW, WSW] |
| **cloud\_cover** | [**CloudCover**](../Models/.md)| The cloud cover reported by the Weather Sensor | [optional] [default to null] [enum: CLEAR, SUNNY, MOSTLY_SUNNY, CLOUDY, MOSTLY_CLOUDY, RAINING] |
| **timestamp** | **Date**| The timestamp for a weather metrics event | [optional] [default to null] |
| **start** | **Date**| The start date-time a weather metrics event | [optional] [default to null] |
| **end** | **Date**| The end date-time for a weather metrics event | [optional] [default to null] |

### Return type

[**List**](../Models/WeatherMetric.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="queryWeatherMetricsBySensorId"></a>
# **queryWeatherMetricsBySensorId**
> List queryWeatherMetricsBySensorId(id, temperature, humidity, wind\_speed, wind\_direction, cloud\_cover, timestamp, start, end)

Query the metrics previously persisted from the specified sensor.

    A list of sensor weather metrics.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor | [default to null] |
| **temperature** | **Integer**| The temperature reported by the Weather Sensor | [optional] [default to null] |
| **humidity** | **Integer**| The humidity percentage reported by the Weather Sensor | [optional] [default to null] |
| **wind\_speed** | **Integer**| The wind speed reported by the Weather Sensor | [optional] [default to null] |
| **wind\_direction** | [**WindDirection**](../Models/.md)| The wind direction reported by the Weather Sensor | [optional] [default to null] [enum: N, NE, NNE, NW, NNW, S, SE, SSE, SW, SSW, ENE, ESE, W, WNW, WSW] |
| **cloud\_cover** | [**CloudCover**](../Models/.md)| The cloud cover reported by the Weather Sensor | [optional] [default to null] [enum: CLEAR, SUNNY, MOSTLY_SUNNY, CLOUDY, MOSTLY_CLOUDY, RAINING] |
| **timestamp** | **Date**| The timestamp for a weather metrics event | [optional] [default to null] |
| **start** | **Date**| The start date-time a weather metrics event | [optional] [default to null] |
| **end** | **Date**| The end date-time for a weather metrics event | [optional] [default to null] |

### Return type

[**List**](../Models/WeatherMetric.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

