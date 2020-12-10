package com.seljabali.templateapplication.ui.weather

import com.orhanobut.logger.Logger
import com.seljabali.core.modules.RxProvider
import com.seljabali.network.MetaWeatherService
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class WeatherRepo(
        private val rxProvider: RxProvider,
        private val api: MetaWeatherService
) {
    var fetchForLocationProcessor: ObservableTransformer<WeatherRepoAction.FetchForLocationAction, WeatherResult>

    init {
        fetchForLocationProcessor = ObservableTransformer {
            it.switchMap { weatherAction: WeatherAction ->
                when (weatherAction) {
                    is WeatherRepoAction.FetchForLocationAction -> {
                        api.getWeatherForWhereOnEarthId(weatherAction.whereOnEarthId)
                                .subscribeOn(rxProvider.ioScheduler())
                                .doOnError { error: Throwable ->
                                    Logger.e(error, error.message ?: "Failed to do api call")
                                }
                                .map<WeatherResult> { response ->
                                    WeatherResult.WeatherForLocationResult(response)
                                }
                                .onErrorReturn {
                                    WeatherResult.ErrorLoadingWeatherForLocationResult
                                }
                                .observeOn(rxProvider.uiScheduler())
                                .startWith(WeatherResult.LoadingWeatherResult)
                    }
                    else -> {
                        Observable.just(WeatherResult.ErrorLoadingWeatherForLocationResult)
                    }
                }
            }
        }
    }
}
