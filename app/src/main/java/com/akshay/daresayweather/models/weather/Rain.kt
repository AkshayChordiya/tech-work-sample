package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Rain(
        @SerializedName("3h") var h: Double = 0.0
)