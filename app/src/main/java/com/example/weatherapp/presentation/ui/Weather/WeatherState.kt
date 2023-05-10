package com.example.weatherapp.presentation.ui.Weather

import com.example.weatherapp.domain.model.Weather

data class WeatherState (
    val weather: Weather? = null,
    val error: Throwable? = null
)