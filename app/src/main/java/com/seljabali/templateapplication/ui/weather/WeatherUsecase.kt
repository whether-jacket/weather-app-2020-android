package com.seljabali.templateapplication.ui.weather

import com.orhanobut.logger.Logger
import com.seljabali.core.modules.RxProvider
import com.seljabali.core.utilities.round
import com.seljabali.core.utilities.time.Formats
import com.seljabali.core.utilities.time.parseZonedDate
import com.seljabali.core.utilities.time.print
import com.seljabali.database.models.LocationDb
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.database.models.WeatherForLocationDb
import com.seljabali.network.MetaWeatherService
import com.seljabali.network.responses.WeatherForLocation
import com.seljabali.templateapplication.ui.weather.WeatherResult.*
import com.seljabali.templateapplication.ui.weather.models.CityRegionWeather
import io.objectbox.Box
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import java.lang.RuntimeException

class WeatherUsecase(
        private val rxProvider: RxProvider,
        private val locationBox: Box<LocationDb>,
        private val weatherForLocationBox: Box<WeatherForLocationDb>,
        private val userPreferencesBox: Box<UserPreferencesDb>,
        private val weatherApiRepo: WeatherRepo,
        private val api: MetaWeatherService
) {
    var fetchAllProcessor: ObservableTransformer<WeatherRepoAction.FetchAllAction, WeatherResult>

    init {
        fetchAllProcessor = ObservableTransformer {
            it.switchMap { weatherAction: WeatherAction ->
                when (weatherAction) {
                    is WeatherRepoAction.FetchAllAction -> {
                        getAllFavoriteLocations()
                                .subscribeOn(rxProvider.ioScheduler())
                                .doOnError { error: Throwable ->
                                    Logger.e(error, error.message ?: "Failed to do api call")
                                }
//                                .map<WeatherResult> { locations: List<LocationDb> ->
                                .map<ArrayList<CityRegionWeather>> { locations: List<LocationDb> ->
                                    val citiesRegionWeather: ArrayList<CityRegionWeather> = ArrayList()
                                    val weatherForLocations = weatherForLocationBox.all
                                    for ((index, location) in locations.iterator().withIndex()) {
                                        val weatherForLocation = weatherForLocations.firstOrNull { weather -> weather.location.target.woeId == location.woeId }
                                        val currentCityRegionWeather = CityRegionWeather(
                                                cityName = location.cityName,
                                                regionName = location.regionName,
                                                woeId = location.woeId,
                                                currentTemperature = weatherForLocation?.theTemp?.round(2).toString() + " F" ?: "",
                                                humidity = weatherForLocation?.humidity?.round(2).toString() ?: "",
                                                windSpeed = weatherForLocation?.windSpeed?.round(2).toString() ?: "",
                                                pressure = weatherForLocation?.pressure?.round(2).toString() ?: "",
                                                dateTime = weatherForLocation?.dateTimeOfFetch?.parseZonedDate()?.print(Formats.MonthDayYear.MMM_D_YYYY_SPACE)  ?: "",
                                        )
                                        citiesRegionWeather.add(index, currentCityRegionWeather)
                                    }
//                                    WeatherForAllLocationsResult(weatherForCities = citiesRegionWeather)
                                    citiesRegionWeather
                                }
                                .flatMap { weatherResult ->
                                    if (weatherResult !is WeatherForAllLocationsResult) {
                                        throw RuntimeException("")
                                    }
                                    val weatherForCities = weatherResult.weatherForCities
                                    for (weatherForCity in weatherForCities) {
                                        if (weatherForCity.currentTemperature.isNotEmpty()) {
                                            return@flatMap Observable.just(weatherForCity)
                                        } else {
                                            return@flatMap Observable.just(weatherForCity)
                                        }
                                    }
                                }
                                .onErrorReturn {
                                    ErrorLoadingWeatherForLocationResult
                                }
                                .observeOn(rxProvider.uiScheduler())
                                .startWith(LoadingWeatherResult)
                    }
                    else -> {
                        Observable.just(ErrorLoadingWeatherForLocationResult)
                    }
                }
            }
        }
    }

    private fun getAllFavoriteLocations(): Observable<List<LocationDb>> =
        Observable.defer {
            val favoriteLocations = locationBox.all.sortedBy { it.position }
            Observable.just(favoriteLocations)
        }
}