package com.seljabali.templateapplication.ui.weather

import com.seljabali.core.modules.RxProvider
import com.seljabali.network.MetaWeatherService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val weatherModule = module {

    viewModel<WeatherViewModel> {
        WeatherViewModel(
            get<RxProvider>(),
            get<WeatherRepo>()
        )
    }

    single<WeatherRepo> {
        WeatherRepo(
            get<RxProvider>(),
            get<MetaWeatherService>()
        )
    }
}
