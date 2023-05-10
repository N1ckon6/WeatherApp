package com.example.weatherapp.presentation.ui.Weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.use_case.implementation.WeatherUseCaseImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val useCaseImpl: WeatherUseCaseImpl
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    fun getWeather(API_KEY: String, city: String) {
        viewModelScope.launch {
            runCatching {
                _state.value = WeatherState()
                useCaseImpl.getWeather(API_KEY, city)
            }.onSuccess {
                _state.value = WeatherState(weather = it)
            }.onFailure {
                _state.value = WeatherState(error = it)
            }
        }
    }
}