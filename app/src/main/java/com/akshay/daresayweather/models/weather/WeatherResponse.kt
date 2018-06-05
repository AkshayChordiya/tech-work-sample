package com.akshay.daresayweather.models.weather

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
        @SerializedName("coord") var coord: Coord = Coord(),
        @SerializedName("sys") var sys: Sys = Sys(),
        @SerializedName("weather") var weather: List<Weather> = listOf(),
        @SerializedName("main") var main: Main = Main(),
        @SerializedName("wind") var wind: Wind = Wind(),
        @SerializedName("rain") var rain: Rain = Rain(),
        @SerializedName("clouds") var clouds: Clouds = Clouds(),
        @SerializedName("dt") var dt: Int = 0, //1369824698
        @SerializedName("id") var id: Int = 0, //1851632
        @SerializedName("name") var name: String = "", //Shuzenji
        @SerializedName("cod") var cod: Int = 0 //200
)