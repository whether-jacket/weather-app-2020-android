package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewState

data class WeatherViewState(
    var currentTemperature: String,
    var isLoadingTemperature: Boolean = false
) : BaseViewState
