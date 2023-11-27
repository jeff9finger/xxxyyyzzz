# WeatherMetric
## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
| **object\_type** | **String** |  | [default to null] |
| **id** | **Long** |  | [optional] [default to null] |
| **sensor\_id** | **Long** | The sensor id of the sensor that is send the data | [optional] [default to null] |
| **timestamp** | **Date** | The timestamp for the event | [optional] [default to null] |
| **temperature** | **Integer** | The reported temperature. | [default to null] |
| **humidity** | **Integer** | The reported humidity. Specified as a integer that represents the percentage. | [default to null] |
| **wind\_speed** | **Integer** | The representation of the wind speed. | [default to null] |
| **wind\_direction** | [**WindDirection**](WindDirection.md) |  | [default to null] |
| **cloud\_cover** | [**CloudCover**](CloudCover.md) |  | [default to null] |

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)

