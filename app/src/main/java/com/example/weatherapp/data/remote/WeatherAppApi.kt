package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherAppApi {

    @GET("/v1/current.json?key={API_KEY}&q={city}&aqi=no")
    suspend fun getCityWeather(@Path("API_KEY") API_KEY: String, @Path("city") city: String) : WeatherDto
}