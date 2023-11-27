# WeatherStatisticApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**queryWeatherStatisticBySensorId**](WeatherStatisticApi.md#queryWeatherStatisticBySensorId) | **GET** /sensors/{id}/weather-statistics | Query the weather metrics previously persisted from the specified sensor |


<a name="queryWeatherStatisticBySensorId"></a>
# **queryWeatherStatisticBySensorId**
> WeatherStatistic queryWeatherStatisticBySensorId(id, statistic\_type, temperature, humidity, wind\_speed, wind\_direction, cloud\_cover, start, end)

Query the weather metrics previously persisted from the specified sensor

    An object that represents the requested statistic type for the data requested.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor | [default to null] |
| **statistic\_type** | [**WeatherStatisticType**](../Models/.md)| The type of statistic to gather | [default to null] [enum: MIN, MAX, SUM, AVERAGE] |
| **temperature** | **Integer**| The temperature reported by the Weather Sensor | [optional] [default to null] |
| **humidity** | **Integer**| The humidity percentage reported by the Weather Sensor | [optional] [default to null] |
| **wind\_speed** | **Integer**| The wind speed reported by the Weather Sensor | [optional] [default to null] |
| **wind\_direction** | [**WindDirection**](../Models/.md)| The wind direction reported by the Weather Sensor | [optional] [default to null] [enum: N, NE, NNE, NW, NNW, S, SE, SSE, SW, SSW, ENE, ESE, W, WNW, WSW] |
| **cloud\_cover** | [**CloudCover**](../Models/.md)| The cloud cover reported by the Weather Sensor | [optional] [default to null] [enum: CLEAR, SUNNY, MOSTLY_SUNNY, CLOUDY, MOSTLY_CLOUDY, RAINING] |
| **start** | **Date**| The start date-time a weather metrics event | [optional] [default to null] |
| **end** | **Date**| The end date-time for a weather metrics event | [optional] [default to null] |

### Return type

[**WeatherStatistic**](../Models/WeatherStatistic.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

