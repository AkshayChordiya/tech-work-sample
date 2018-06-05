package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Sys(
        @SerializedName("country") var country: String = "", //JP
        @SerializedName("sunrise") var sunrise: Int = 0, //1369769524
        @SerializedName("sunset") var sunset: Int = 0 //1369821049
)