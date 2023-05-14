package com.example.weatherapp.presentation.ui.weather

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Looper
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

@SuppressLint("MissingPermission")
@Composable
fun WeatherScreen(
    modifier: Modifier
) {
    val viewModel: WeatherScreenViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity = context as Activity
    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            startLocationUpdates(fusedLocationClient, viewModel)
        } else {
            Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
    LaunchedEffect(Unit) {
        launcherMultiplePermissions.launch(permissions)
    }
    val state = viewModel.state.value
    WeatherScreenContent(modifier = modifier, state = state)

}


@SuppressLint("MissingPermission")
private fun startLocationUpdates(
    fusedLocationClient: FusedLocationProviderClient,
    viewModel: WeatherScreenViewModel
) {
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                viewModel.getWeather(lo.latitude, lo.longitude)
                fusedLocationClient.removeLocationUpdates(this)
            }
        }
    }
    locationCallback.let {
        val locationRequest =
            LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0).build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            it,
            Looper.getMainLooper()
        )
    }
}

@Composable
fun WeatherScreenContent(modifier: Modifier, state: WeatherState) {
    Column(
        modifier = modifier
    ) {
        state.weather?.location?.let {
            Text(text = it.name)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = state.weather?.current?.temp_c.toString())
    }
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreenContent(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta),
        state = WeatherState()
    )
}
