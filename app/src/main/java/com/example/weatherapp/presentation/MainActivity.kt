package com.example.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherapp.common.Constants
import com.example.weatherapp.common.Routes
import com.example.weatherapp.presentation.theme.WeatherAppTheme
import com.example.weatherapp.presentation.ui.Weather.WeatherScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(startDestination = Routes.WeatherRoute.route, navController = navController) {
                composable(
                    route = Routes.WeatherRoute.route,
                    arguments = listOf(
                        navArgument("API_KEY") { type = NavType.StringType },
                        navArgument("city") { type = NavType.StringType }
                    )
                ) {
                    val API_KEY = Constants.API_KEY
                    val city = it.arguments?.getString("city")
                    if (city != null) {
                        WeatherScreen(API_KEY = API_KEY, city = city)
                    }
                }
            }
        }
    }
}