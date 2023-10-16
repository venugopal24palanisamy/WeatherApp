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

    companion object {

        var apiService: ApiService? = null
        fun getInstance(): ApiService {
            if (apiService == null) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                apiService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(
                        OkHttpClient
                            .Builder().addInterceptor(HeaderInterceptor())
                            .addInterceptor(loggingInterceptor)
                            .build()
                    )
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }

        class HeaderInterceptor : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                val request = chain.request()
                    .newBuilder()
                    .addHeader(API_KEY_HEADER, API_KEY)
                    .addHeader(HOST_HEADER, API_HOST)
                    .build()
                return chain.proceed(request)
            }
        }

    }

}