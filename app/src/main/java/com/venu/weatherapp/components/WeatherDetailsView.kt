package com.venu.weatherapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.venu.weatherapp.model.weatherDetails.WeatherDetails

@Composable
fun WeatherDetailsView(weatherDetails: WeatherDetails?) {
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
            fontSize = 16.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Medium
        )

    }

}