package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.modules.RxProvider
import com.seljabali.core.mvi.BaseViewModel
import com.seljabali.core.utilities.round
import com.seljabali.core.utilities.time.Formats
import com.seljabali.core.utilities.time.parseZonedDate
import com.seljabali.core.utilities.time.print
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class WeatherViewModel(
    private val rxProvider: RxProvider,
    private val repo: WeatherRepo
) : BaseViewModel<WeatherViewEvent, WeatherViewState, WeatherSideEffect>(rxProvider) {

    private var actionProcessor: ObservableTransformer<WeatherAction, WeatherResult>
    private val initialViewState = WeatherViewState(currentTemperature = "TBD")

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
    private fun getActionFromViewEvent(viewEvent: WeatherViewEvent): List<WeatherAction> {
        val action: WeatherAction = when (viewEvent) {
            is WeatherViewEvent.LoadSfWeatherEvent -> WeatherRepoAction.FetchForLocationAction(2487956)
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
                    actions.ofType(WeatherRepoAction.FetchForLocationAction::class.java)
                        .compose(repo.fetchForLocationProcessor),
                    actions.ofType(WeatherRepoAction.FetchForSearchLocationAction::class.java)
                        .compose(repo.fetchForSearchLocationProcessor)
                )
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
                currentTemperature = "Loading"
                isLoadingTemperature = true
            }
            is WeatherResult.WeatherForLocationResult -> newState.apply {
                val weather = result.response.consolidatedWeather[0]
                city = result.response.cityTitle
                greaterRegion = result.response.parentRegion.title
                currentTemperature = weather.maxTemp.round(2).toString() + " F"
                humidity = weather.humidity.round(2).toString()
                windSpeed = weather.windSpeed.round(2).toString()
                pressure = weather.airPressure.round(2).toString()
                dateTime = result.response.dateTime.parseZonedDate()?.print(Formats.MonthDayYear.MMM_D_YYYY_SPACE) ?: ""
                isLoadingTemperature = false
            }
            is WeatherResult.ErrorLoadingWeatherForLocationResult -> newState.apply {
                isLoadingTemperature = false
                currentTemperature = "Unknown"
                sendSideEffect(WeatherSideEffect.ShowToast("Error Loading Weather"))
            }
        }
        return newState
    }
}
