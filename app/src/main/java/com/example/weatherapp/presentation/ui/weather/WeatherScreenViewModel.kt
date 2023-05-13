package com.example.weatherapp.presentation.ui.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.common.Constants
import com.example.weatherapp.domain.use_case.implementation.WeatherUseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val useCaseImpl: WeatherUseCaseImpl
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            runCatching {
                _state.value = WeatherState()
                useCaseImpl.getWeather(Constants.API_KEY, lat, lon)
            }.onSuccess {
                _state.value = WeatherState(weather = it)
            }.onFailure {
                _state.value = WeatherState(error = it)
            }
        }
    }
}