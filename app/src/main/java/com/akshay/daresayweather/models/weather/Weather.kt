package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class Weather(
        @SerializedName("id") var id: Int = 0, //804
        @SerializedName("main") var main: String = "", //clouds
        @SerializedName("description") var description: String = "", //overcast clouds
        @SerializedName("icon") var icon: String = "" //04n
)