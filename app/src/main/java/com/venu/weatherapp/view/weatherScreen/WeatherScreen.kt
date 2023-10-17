package com.venu.weatherapp.view.weatherScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.venu.weatherapp.R
import com.venu.weatherapp.components.EmptyState
import com.venu.weatherapp.components.SearchBar
import com.venu.weatherapp.components.WeatherDetailsView
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WeatherScreen(navHostController: NavHostController) {
    val scope = rememberCoroutineScope()
    //val weatherViewModel = WeatherViewModel(WeatherRepository(ApiService.getInstance()))
    val weatherViewModel : WeatherViewModel = hiltViewModel()
    val weatherData = weatherViewModel.weatherDetails.observeAsState()
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader_weather))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true
    )
    Surface {
        Column {
            SearchBar(
                text = weatherViewModel.locationSearchName,
                onTextChange = { weatherViewModel.locationSearchName = it },
                onSearch = { scope.launch { if (weatherViewModel.validate()) weatherViewModel.searchByCityName() } },
                supportingErrorText = weatherViewModel.locationSearchNameError,
                isSearchError = weatherViewModel.isLocationSearchNameError
            )
            if (weatherViewModel.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    //CircularProgressIndicator()
                    LottieAnimation(
                        composition,
                        progress,
                        modifier = Modifier.size(400.dp)
                    )
                }
            } else {
                weatherData.value?.let {
                    Column {
                        WeatherDetailsView(weatherData.value)
                    }
                } ?: EmptyState()
            }

        }
    }

}


