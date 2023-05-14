package com.example.weatherapp.domain.repository

import com.example.weatherapp.common.IoDispatcher
import com.example.weatherapp.data.remote.WeatherAppApi
import com.example.weatherapp.data.repository.WeatherAppRepository
import com.example.weatherapp.domain.mappers.toWeather
import com.example.weatherapp.domain.model.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherAppRepositoryImpl @Inject constructor(
    private val api: WeatherAppApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : WeatherAppRepository {
    override suspend fun getWeatherInCity(API_KEY: String, coordinates: String): Weather =
        withContext(ioDispatcher) {
            api.getCityWeather(API_KEY, coordinates).toWeather()
        }
}