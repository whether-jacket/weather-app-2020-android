package com.seljabali.templateapplication

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.seljabali.core.modules.utilsModule
import com.seljabali.database.databaseModule
import com.seljabali.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TemplateApplication: Application() {

    private val modules = listOf(
        databaseModule,
        networkModule,
        utilsModule
    )

    override fun onCreate() {
        super.onCreate()
        setupLibraries()
    }

    private fun setupLibraries() {
        Logger.addLogAdapter(AndroidLogAdapter())
        startKoin {
            androidLogger()
            androidContext(this@TemplateApplication)
            modules(modules)
        }
    }
}