package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.mvi.BaseViewStateBinder
import com.seljabali.templateapplication.ui.weather.cityregionadapter.CityRegion
import com.seljabali.templateapplication.ui.weather.models.CityRegionWeather

class WeatherViewStateBinder(private val weatherViewApi: WeatherViewApi) :
    BaseViewStateBinder<WeatherViewState> {

    override fun setViewState(viewState: WeatherViewState) {
        setTemperature(viewState)
        setProgressBar(viewState)
    }

    private fun setTemperature(viewState: WeatherViewState) {
        with(viewState) {
            if (isLoadingTemperature) {
                weatherViewApi.apply {
                    setCityRegionsVisibility(false)
                    setTemperature("")
                    setDateTime("")
                    setHumidity("")
                    setPressure("")
                    setWindSpeed("")
                    setWeatherImageVisibility(false)
                    setHumidityTitleVisibility(false)
                    setPressureTitleVisibility(false)
                    setWindSpeedTitleVisibility(false)
                }
                return
            }
            val currentCityRegion = viewState.cityRegionWeatherList[selectedCityRegionPosition]
            weatherViewApi.apply {
                setCityRegionsVisibility(true)
                setCityRegions(getCityRegionsFromWeather(viewState.cityRegionWeatherList))
                setTemperature(currentCityRegion.currentTemperature)
                setDateTime(currentCityRegion.dateTime)
                setHumidity(currentCityRegion.humidity)
                setPressure(currentCityRegion.pressure)
                setWindSpeed(currentCityRegion.windSpeed)
                setWeatherImageVisibility(true)
                setHumidityTitleVisibility(true)
                setPressureTitleVisibility(true)
                setWindSpeedTitleVisibility(true)
            }
        }
    }

    private fun setProgressBar(viewState: WeatherViewState) {
        with(viewState) {
            weatherViewApi.setProgressBarVisibility(isLoadingTemperature)
        }
    }

    private fun getCityRegionsFromWeather(cityRegionWeatherList: List<CityRegionWeather>): ArrayList<CityRegion> {
        val cityRegionsList: ArrayList<CityRegion> = ArrayList()
        for (weather in cityRegionWeatherList) {
            cityRegionsList.add(CityRegion(cityName = weather.cityName, regionName = weather.regionName))
        }
        return cityRegionsList
    }
}
