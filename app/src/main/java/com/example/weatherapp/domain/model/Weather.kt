package com.example.weatherapp.domain.model

import com.example.weatherapp.data.remote.dto.Current
import com.example.weatherapp.data.remote.dto.Location

data class Weather(
    val location: Location,
    val current: Current
)