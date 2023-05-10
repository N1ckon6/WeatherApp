package com.example.weatherapp.domain.use_case.implementation

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.repository.WeatherAppRepositoryImpl
import com.example.weatherapp.domain.use_case.interfaces.WeatherUseCase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherUseCaseImpl @Inject constructor(
    private val repository: WeatherAppRepositoryImpl
) : WeatherUseCase {
    override suspend fun getWeather(API_KEY: String, city: String): Weather = repository.getWeatherInCity(API_KEY, city)
}