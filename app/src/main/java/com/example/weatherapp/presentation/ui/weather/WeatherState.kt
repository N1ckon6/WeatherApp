package com.example.weatherapp.presentation.ui.weather

import com.example.weatherapp.domain.model.Weather

data class WeatherState (
    val weather: Weather? = null,
    val error: Throwable? = null
)