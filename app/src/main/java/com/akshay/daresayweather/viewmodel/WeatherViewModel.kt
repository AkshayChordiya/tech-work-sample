package com.akshay.daresayweather.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import com.akshay.daresayweather.models.Resource
import com.akshay.daresayweather.models.live.LocationLiveData
import com.akshay.daresayweather.models.weather.WeatherResponse
import com.akshay.daresayweather.repo.WeatherRepository

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    // Single source of truth for weather information.
    private val weatherRepository = WeatherRepository()

    // Location
    private val locationData = LocationLiveData(application)

    // Weather
    private val currentWeather: LiveData<Resource<WeatherResponse>>

    init {
        // Fetches weather information when location is available.
        currentWeather = Transformations.switchMap(locationData) {
            it.data?.let {
                weatherRepository.getCurrentWeather(it)
            }
        }
    }

    /**
     * Returns current location.
     */
    fun getLocation() = locationData

    /**
     * Returns current weather information.
     */
    fun getCurrentWeather() = currentWeather
}