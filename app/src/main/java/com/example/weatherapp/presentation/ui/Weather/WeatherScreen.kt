package com.example.weatherapp.presentation.ui.Weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherScreenViewModel = hiltViewModel(),
    API_KEY: String,
    city: String
) {
    LaunchedEffect(Unit) {
        viewModel.getWeather(API_KEY, city)
    }
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp)
        ) {
            state.weather?.location?.let {
                Text(text = it.name)
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = state.weather?.current?.temp_c.toString())
        }
    }
}
