package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.modules.RxProvider
import com.seljabali.core.mvvm.BaseViewModel
import com.seljabali.core.utilities.round
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
    private fun getActionFromViewEvent(viewEvent: WeatherViewEvent): WeatherAction {
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
                        .compose(repo.fetForLocationProcessor),
                    actions.ofType(WeatherSideEffectsAction.ShowToast::class.java)
                        .compose(repo.handleShowToastProcessor)
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
                currentTemperature =
                    result.response.consolidatedWeather[0].maxTemp.round(2).toString()
                isLoadingTemperature = false
                sendSideEffect(WeatherSideEffect.ShowToast("Loaded Weather"))
            }
            is WeatherResult.ErrorLoadingWeatherForLocationResult -> newState.apply {
                isLoadingTemperature = false
                currentTemperature = "Unknown"
                sendSideEffect(WeatherSideEffect.ShowToast("Error Loading Weather"))
            }
            is WeatherResult.ShowToastResult -> {
                sendSideEffect(WeatherSideEffect.ShowToast(result.message))
            }
        }
        return newState
    }
}
