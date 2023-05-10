package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.domain.model.Weather

fun WeatherDto.toWeather() = Weather(location, current)