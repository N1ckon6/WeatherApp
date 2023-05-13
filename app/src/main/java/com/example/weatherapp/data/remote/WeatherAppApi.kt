package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAppApi {

    @GET("/v1/current.json")
    suspend fun getCityWeather(
        @Query("key") API_KEY: String,
        @Query("q") coordinates: String,
        @Query("aqi") aqi: String = "no"
    ): WeatherDto
}