package com.venu.weatherapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.venu.weatherapp.R
import com.venu.weatherapp.model.weatherDetails.WeatherDetails
import com.venu.weatherapp.utils.MyFunctions

@Composable
fun WeatherDetailsView(weatherDetails: WeatherDetails?) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.thunder_strom_weather))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
        restartOnPlay = true
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        Text(
            text = "${weatherDetails!!.current.temp_c}°",
            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        //https://rapidapi.com/weatherapi/api/weatherapi-com
        //https://dribbble.com/shots/13972298-Weather-App-UI-Animation
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = weatherDetails.current.condition.text,
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "${MyFunctions.getCurrentDay()} - ${weatherDetails.location.name}",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "Humidity - ${weatherDetails.current.humidity}%",
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(15.dp))
        LottieAnimation(
            composition,
            progress,
            modifier = Modifier.size(200.dp)
        )
    }

}