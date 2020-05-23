package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseSideEffect

sealed class WeatherSideEffect : BaseSideEffect {
    data class ShowToast(val message: String) : WeatherSideEffect()
}
