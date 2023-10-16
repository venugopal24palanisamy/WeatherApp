package com.venu.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.venu.weatherapp.view.weatherScreen.WeatherScreen

@Composable
fun AppNavigation(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = "weather"
    ) {
        composable("weather") {
            WeatherScreen(navHostController)
        }
    }
}