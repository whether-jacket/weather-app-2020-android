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
                weatherViewApi?.setCity("")
                weatherViewApi?.setParentRegion("")
                weatherViewApi?.setTemperature("")
                weatherViewApi?.setDateTime("")
                weatherViewApi?.setHumidity("")
                weatherViewApi?.setPressure("")
                weatherViewApi?.setWindSpeed("")
                weatherViewApi?.setWeatherImageVisibility(false)
                weatherViewApi?.setHumidityTitleVisibility(false)
                weatherViewApi?.setPressureTitleVisibility(false)
                weatherViewApi?.setWindSpeedTitleVisibility(false)
                return
            }
            weatherViewApi?.setCity(city)
            weatherViewApi?.setParentRegion(greaterRegion)
            weatherViewApi?.setTemperature(currentTemperature)
            weatherViewApi?.setDateTime(dateTime)
            weatherViewApi?.setHumidity(humidity)
            weatherViewApi?.setPressure(pressure)
            weatherViewApi?.setWindSpeed(windSpeed)
            weatherViewApi?.setWeatherImageVisibility(true)
            weatherViewApi?.setHumidityTitleVisibility(true)
            weatherViewApi?.setPressureTitleVisibility(true)
            weatherViewApi?.setWindSpeedTitleVisibility(true)
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
