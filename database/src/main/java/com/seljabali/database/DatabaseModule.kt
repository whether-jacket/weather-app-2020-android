package com.seljabali.database

import com.seljabali.core.utilities.App
import com.seljabali.database.models.LocationDb
import com.seljabali.database.models.MyObjectBox
import com.seljabali.database.models.UserPreferencesDb
import com.seljabali.database.models.WeatherForLocationDb
import io.objectbox.Box
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val DB_USER_PREFERENCES_BOX = "UserPreferences"
val DB_WEATHER_FOR_LOCATION_BOX = "WeatherForLocation"
val DB_LOCATION_BOX = "Location"

val databaseModule = module {

    single<Box<LocationDb>>(named(DB_LOCATION_BOX)) {
        get<BoxStore>().boxFor(LocationDb::class.java)
    }

    single<Box<WeatherForLocationDb>>(named(DB_WEATHER_FOR_LOCATION_BOX)) {
        get<BoxStore>().boxFor(WeatherForLocationDb::class.java)
    }

    single<Box<UserPreferencesDb>>(named(DB_USER_PREFERENCES_BOX)) {
        get<BoxStore>().boxFor(UserPreferencesDb::class.java)
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