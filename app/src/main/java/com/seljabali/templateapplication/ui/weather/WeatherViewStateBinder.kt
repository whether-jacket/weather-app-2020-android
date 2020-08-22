package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewStateBinder

class WeatherViewStateBinder : BaseViewStateBinder<WeatherViewState> {

    private var weatherViewApi: WeatherViewApi? = null

    fun setViewApi(viewApi: WeatherViewApi) {
        this.weatherViewApi = viewApi
    }

    override fun setViewState(viewState: WeatherViewState) {
        setTemperature(viewState)
        setProgressBar(viewState)
    }

    private fun setTemperature(viewState: WeatherViewState) {
        with(viewState) {
            if (isLoadingTemperature) {
                weatherViewApi?.setTemperature(currentTemperature)
            } else {
                val weatherSummary = "$currentTemperature C"
                weatherViewApi?.setTemperature(weatherSummary)
            }
        }
    }

    private fun setProgressBar(viewState: WeatherViewState) {
        with(viewState) {
            weatherViewApi?.setProgressBarVisibility(isLoadingTemperature)
        }
    }

    override fun unbindView() {
        weatherViewApi = null
    }
}
