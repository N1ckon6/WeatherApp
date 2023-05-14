package com.example.weatherapp.data.repository

import com.example.weatherapp.domain.model.Weather

interface WeatherAppRepository {

    suspend fun getWeatherInCity(API_KEY: String, coordinates: String) : Weather
}