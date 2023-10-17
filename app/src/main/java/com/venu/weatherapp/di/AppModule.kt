package com.venu.weatherapp.di

import com.venu.weatherapp.api.ApiService
import com.venu.weatherapp.utils.Constants
import com.venu.weatherapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return Retrofit.Builder()
            .baseUrl(BASE_URL).client(
                OkHttpClient
                    .Builder()
                    .addInterceptor(loggingInterceptor)
                    .addInterceptor(HeaderInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)

}

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(Constants.API_KEY_HEADER, Constants.API_KEY)
            .addHeader(Constants.HOST_HEADER, Constants.API_HOST)
            .build()
        return chain.proceed(request)
    }
}