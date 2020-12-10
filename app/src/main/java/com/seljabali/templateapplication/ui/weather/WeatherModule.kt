package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.modules.RxProvider
import com.seljabali.database.DB_LOCATION_BOX
import com.seljabali.database.DB_USER_PREFERENCES_BOX
import com.seljabali.database.DB_WEATHER_FOR_LOCATION_BOX
import com.seljabali.database.models.LocationDb
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.database.models.WeatherForLocationDb
import com.seljabali.network.MetaWeatherService
import io.objectbox.Box
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val weatherModule = module {

    viewModel<WeatherViewModel> {
        WeatherViewModel(
                rxProvider = get<RxProvider>(),
                weatherRepo = get<WeatherRepo>(),
                weatherUsecase = get<WeatherUsecase>()
        )
    }

    single<WeatherRepo> {
        WeatherRepo(
                rxProvider = get<RxProvider>(),
                api = get<MetaWeatherService>()
        )
    }

    single<WeatherUsecase> {
        WeatherUsecase(
                rxProvider = get<RxProvider>(),
                locationBox = get<Box<LocationDb>>(named(DB_LOCATION_BOX)),
                weatherForLocationBox = get<Box<WeatherForLocationDb>>(named(DB_WEATHER_FOR_LOCATION_BOX)),
                userPreferencesBox = get<Box<UserPreferencesDb>>(named(DB_USER_PREFERENCES_BOX)),
                weatherApiRepo = get<WeatherRepo>(),
                api = get<MetaWeatherService>()
        )
    }
}
