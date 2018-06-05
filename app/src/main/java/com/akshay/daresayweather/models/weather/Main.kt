package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Main(
        @SerializedName("temp") var temp: Double = 0.0,
        @SerializedName("humidity") var humidity: Double = 0.0,
        @SerializedName("pressure") var pressure: Double = 0.0,
        @SerializedName("temp_min") var tempMin: Double = 0.0,
        @SerializedName("temp_max") var tempMax: Double = 0.0
)