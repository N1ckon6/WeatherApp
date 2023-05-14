package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.common.Routes
import com.example.weatherapp.presentation.ui.weather.WeatherScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(startDestination = Routes.WeatherRoute.route, navController = navController) {
                composable(
                    route = Routes.WeatherRoute.route,
                ) {
                    WeatherScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Magenta)
                    )
                }
            }
        }
    }
}