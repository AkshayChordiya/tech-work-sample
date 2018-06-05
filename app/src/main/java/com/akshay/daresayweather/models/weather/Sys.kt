package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Sys(
        @SerializedName("country") var country: String = "",
        @SerializedName("sunrise") var sunrise: Double = 0.0,
        @SerializedName("sunset") var sunset: Double = 0.0
)