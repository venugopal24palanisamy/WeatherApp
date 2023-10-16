package com.venu.weatherapp.view.weatherScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.venu.weatherapp.model.weatherDetails.WeatherDetails
import com.venu.weatherapp.repository.weatherRepository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel() {

    private val _weatherDetails = MutableLiveData<WeatherDetails>()
    val weatherDetails: LiveData<WeatherDetails>
        get() = _weatherDetails

    var locationSearchName by mutableStateOf("")
    var locationSearchNameError by mutableStateOf("")
    var latLng by mutableStateOf("")
    var isLocationSearchNameError by mutableStateOf(false)

    var isLoading by mutableStateOf(false)

    fun validate(): Boolean {
        if (locationSearchName.isEmpty()) {
            locationSearchNameError = "Enter City Name"
            isLocationSearchNameError = true
            return false
        } else {
            locationSearchNameError = ""
            isLocationSearchNameError = false
        }
        return true
    }

    fun searchByCityName() {
        viewModelScope.launch {
            isLoading = true
            weatherRepository.searchByCityName(locationSearchName).let {
                isLoading = false
                //_locationDetails.value = if (it.isSuccessful) it.body() else null
                latLng = if (it.isSuccessful) "${it.body()!![0].lat},${it.body()!![0].lon}" else ""
                getWeatherReportByLatLng()
            }
        }
    }


    private fun getWeatherReportByLatLng() {
        viewModelScope.launch {
            isLoading = true
            weatherRepository.weatherReportByLatLng(latLng).let {
                isLoading = false
                _weatherDetails.value = if (it.isSuccessful) it.body() else null
            }
        }
    }

}