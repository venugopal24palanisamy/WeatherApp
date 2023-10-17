package com.venu.weatherapp.utils

import androidx.compose.runtime.remember
import java.time.LocalDate
import java.time.LocalTime

class MyFunctions {
    companion object {
        fun getCurrentDay(): String = LocalDate.now().dayOfWeek.name
    }
}