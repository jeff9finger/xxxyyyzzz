# SensorApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addSensor**](SensorApi.md#addSensor) | **POST** /sensors | Add a new sensor |
| [**getSensor**](SensorApi.md#getSensor) | **GET** /sensors/{id} | Get sensor details |
| [**querySensors**](SensorApi.md#querySensors) | **GET** /sensors | Query all sensors |


<a name="addSensor"></a>
# **addSensor**
> Sensor addSensor(Sensor)

Add a new sensor

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **Sensor** | [**Sensor**](../Models/Sensor.md)| Sensor details | |

### Return type

[**Sensor**](../Models/Sensor.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

<a name="getSensor"></a>
# **getSensor**
> Sensor getSensor(id)

Get sensor details

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor | [default to null] |

### Return type

[**Sensor**](../Models/Sensor.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

<a name="querySensors"></a>
# **querySensors**
> List querySensors(id, name)

Query all sensors

    Query the information about the actual sensors.

### Parameters

|Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **Long**| ID of the sensor | [optional] [default to null] |
| **name** | **String**| The name of the Weather Sensor | [optional] [default to null] |

### Return type

[**List**](../Models/Sensor.md)

### Authorization

No authorization required

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json

