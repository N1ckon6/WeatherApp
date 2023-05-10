package com.example.weatherapp.domain.use_case.interfaces

import com.example.weatherapp.domain.model.Weather

interface WeatherUseCase {

    suspend fun getWeather(API_KEY: String, city: String): Weather
}