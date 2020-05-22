package com.seljabali.database

import com.seljabali.core.utilities.App
import com.seljabali.database.models.MyObjectBox
import com.seljabali.database.models.UserPreferences
import com.seljabali.database.models.WeatherForLocationDb
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DB_USER_PREFERENCES_BOX = "UserPreferences"
val DB_WEATHER_FOR_LOCATION_BOX = "WeatherForLocation"

val databaseModule = module {

    single<Box<WeatherForLocationDb>>(named("WeatherForLocation")) {
        get<BoxStore>().boxFor(WeatherForLocationDb::class.java)
    }

    single<Box<UserPreferences>>(named("UserPreferences")) {
        get<BoxStore>().boxFor(UserPreferences::class.java)
    }

    single<BoxStore> {
        val appContext = androidContext().applicationContext
        val box = MyObjectBox.builder().androidContext(appContext).build()
        if (App.isInDebugMode()) {
            AndroidObjectBrowser(box).start(appContext)
        }
        box
    }
}