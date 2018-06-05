package com.akshay.daresayweather.api

import android.arch.lifecycle.LiveData
import com.akshay.daresayweather.models.Resource
import com.akshay.daresayweather.models.weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    /**
     * Returns current weather information for the [lat], [lon].
     */
    @GET("/weather")
    fun getCurrentWeather(@Query("lat") lat: String, @Query("lon") lon: String, @Query("key") apiKey: String): LiveData<Resource<WeatherResponse>>
}