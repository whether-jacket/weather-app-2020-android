package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvvm.BaseViewStateBinder

class WeatherViewStateBinder : BaseViewStateBinder<WeatherViewState> {

    private var weatherViewApi: WeatherViewApi? = null

    fun setViewApi(viewApi: WeatherViewApi) {
        this.weatherViewApi = viewApi
    }

    override fun setViewState(viewState: WeatherViewState) {
        with(viewState) {
            val weatherSummary = "Weather in SF: ${currentTemperature} C"
            weatherViewApi?.setText(weatherSummary)
            weatherViewApi?.setProgressBarVisibility(isLoadingTemperature)
        }
    }

    override fun unbindView() {
        weatherViewApi = null
    }
}
