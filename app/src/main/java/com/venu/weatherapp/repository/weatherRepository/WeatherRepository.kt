package com.venu.weatherapp.repository.weatherRepository

import com.venu.weatherapp.api.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun searchByCityName(searchName: String?) = apiService.getCityDetailByName(searchName)
    suspend fun weatherReportByLatLng(latLng: String?) = apiService.getWeatherReportDetail(latLng)
}