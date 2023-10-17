package com.venu.weatherapp.utils


import java.time.LocalDate


class MyFunctions {
    companion object {
        fun getCurrentDay(): String = LocalDate.now().dayOfWeek.name
    }
}