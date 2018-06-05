package com.akshay.daresayweather.repo

import android.location.Location
import com.akshay.daresayweather.BuildConfig
import com.akshay.daresayweather.api.RetrofitClient
import com.akshay.daresayweather.api.WeatherService
import com.akshay.daresayweather.utils.create

/**
 * Single source of truth to fetch data related to weather.
 */
class WeatherRepository {

    private var weatherService = RetrofitClient.getRetrofit().create<WeatherService>()

    /**
     * Gets the current weather information of [location] from web.
     */
    fun getCurrentWeather(location: Location) = weatherService.getCurrentWeather(location.latitude.toString(), location.longitude.toString(), BuildConfig.WEATHER_API_KEY)
}