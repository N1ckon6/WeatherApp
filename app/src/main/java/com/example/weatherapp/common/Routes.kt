package com.example.weatherapp.common

import okhttp3.Route

sealed class Routes(val route: String) {
    object WeatherRoute: Routes("weather")
}