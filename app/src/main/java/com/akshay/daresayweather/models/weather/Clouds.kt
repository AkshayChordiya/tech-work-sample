package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Clouds(
        @SerializedName("all") var all: Int = 0 //92
)