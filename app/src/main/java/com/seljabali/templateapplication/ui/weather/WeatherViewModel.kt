package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.modules.RxProvider
import com.seljabali.core.mvi.BaseViewModel
import com.seljabali.core.utilities.round
import com.seljabali.core.utilities.time.Formats
import com.seljabali.core.utilities.time.parseZonedDate
import com.seljabali.core.utilities.time.print
import com.seljabali.network.responses.WeatherForLocation
import com.seljabali.templateapplication.ui.weather.WeatherRepoAction.ChangeLocationAction
import com.seljabali.templateapplication.ui.weather.WeatherRepoAction.FetchForLocationAction
import com.seljabali.templateapplication.ui.weather.models.CityRegionWeather
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class WeatherViewModel(
    private val rxProvider: RxProvider,
    private val weatherUsecase: WeatherUsecase,
    private val weatherRepo: WeatherRepo
) : BaseViewModel<WeatherViewEvent, WeatherViewState, WeatherSideEffect>(rxProvider) {

    private var actionProcessor: ObservableTransformer<WeatherAction, WeatherResult>
    private val initialViewState = WeatherViewState(isLoadingTemperature = true)

    init {
        actionProcessor = getResultFromAction()

        viewEventSubject
            .map { getActionFromViewEvent(it) }
            .compose(actionProcessor)
            .map {
                val newViewState = getViewStateFromResult(it)
                sendViewState(newViewState)
            }
            .replay(1)
            .autoConnect(0)
    }

    /**
     *  View Event -> Action
     */
    private fun getActionFromViewEvent(viewEvent: WeatherViewEvent): WeatherAction {
        val action: WeatherAction = when (viewEvent) {
            is WeatherViewEvent.LoadWeatherPageEvent -> WeatherRepoAction.FetchAllAction
            is WeatherViewEvent.LoadCityPositionEvent -> ChangeLocationAction(viewEvent.position)
            else -> WeatherAction.NoOperationAction
        }
        return action
    }

    /**
     *  Action -> Result
     */
    private fun getResultFromAction(): ObservableTransformer<WeatherAction, WeatherResult> =
        ObservableTransformer { actions ->
            actions.publish {
                Observable.merge(
                    actions.ofType(WeatherRepoAction.FetchAllAction::class.java)
                        .compose(weatherUsecase.fetchAllProcessor),
                    actions.ofType(FetchForLocationAction::class.java)
                        .compose(weatherRepo.fetchForLocationProcessor),
                    actions.ofType(ChangeLocationAction::class.java)
                        .compose(getFetchForLocationActionTransformer())
                )
            }
        }

    private fun getFetchForLocationActionTransformer(): ObservableTransformer<in ChangeLocationAction, out WeatherResult> =
            ObservableTransformer {
                it.switchMap { action: ChangeLocationAction ->
                    Observable.just(WeatherResult.NewLocationResult(locationPosition = action.newPosition))
                }
            }

    /**
     *  Result -> View State
     */
    private fun getViewStateFromResult(result: WeatherResult): WeatherViewState {
        val currentState: WeatherViewState = viewStateSubject.value ?: initialViewState
        val newState = currentState.copy()
        when (result) {
            is WeatherResult.LoadingWeatherResult -> newState.apply {
                isLoadingTemperature = true
            }
            is WeatherResult.WeatherForLocationResult -> newState.apply {
                val cityRegionWeather = getCityRegionWeather(result.response)
                if (cityRegionWeatherList.contains(cityRegionWeather)) {
                    val position = cityRegionWeatherList.indexOf(cityRegionWeather)
                    cityRegionWeatherList[position] = cityRegionWeather
                } else {
                    cityRegionWeatherList.add(cityRegionWeather)
                }
                isLoadingTemperature = false
            }
            is WeatherResult.WeatherForAllLocationsResult -> newState.apply {
                isLoadingTemperature = false
                cityRegionWeatherList = result.weatherForCities
                selectedCityRegionPosition = 0
            }
            is WeatherResult.NewLocationResult -> newState.apply {
                selectedCityRegionPosition = result.locationPosition
            }
            is WeatherResult.ErrorLoadingWeatherForLocationResult -> newState.apply {
                isLoadingTemperature = false
                sendSideEffect(WeatherSideEffect.ShowToast("Error Loading Weather"))
            }
        }
        return newState
    }

    private fun getCityRegionWeather(response: WeatherForLocation): CityRegionWeather {
        val weather = response.consolidatedWeather[0]
        return CityRegionWeather(
            cityName = response.cityTitle,
            regionName = response.parentRegion.title,
            woeId = response.whereOnEarthId,
            // TODO: Take into account user preferences and conversions if need be
            currentTemperature = weather.maxTemp.round(2).toString() + " F",
            humidity = weather.humidity.round(2).toString(),
            windSpeed = weather.windSpeed.round(2).toString(),
            pressure = weather.airPressure.round(2).toString(),
            dateTime = response.dateTime.parseZonedDate()?.print(Formats.MonthDayYear.MMM_D_YYYY_SPACE) ?: ""
        )
    }
}
