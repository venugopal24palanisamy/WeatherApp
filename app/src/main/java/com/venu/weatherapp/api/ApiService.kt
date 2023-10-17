package com.venu.weatherapp.api

import com.venu.weatherapp.model.locationDetails.LocationData
import com.venu.weatherapp.model.weatherDetails.WeatherDetails
import com.venu.weatherapp.utils.Constants.API_HOST
import com.venu.weatherapp.utils.Constants.API_KEY
import com.venu.weatherapp.utils.Constants.API_KEY_HEADER
import com.venu.weatherapp.utils.Constants.BASE_URL
import com.venu.weatherapp.utils.Constants.GET_WEATHER_BY_CITY_DETAILS
import com.venu.weatherapp.utils.Constants.HOST_HEADER
import com.venu.weatherapp.utils.Constants.SEARCH_BY_CITY_NAME
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(SEARCH_BY_CITY_NAME)
    suspend fun getCityDetailByName(@Query("q") cityName: String?): Response<LocationData>
    @GET(GET_WEATHER_BY_CITY_DETAILS)
    suspend fun getWeatherReportDetail(@Query("q") locationDetails: String?): Response<WeatherDetails>
}