package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseViewState

data class WeatherViewState(
    var currentTemperature: String,
    var isLoadingTemperature: Boolean = false
) : BaseViewState
