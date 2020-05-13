package com.seljabali.templateapplication

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.hawk.GsonParser
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.seljabali.core.modules.utilsModule
import com.seljabali.core.utilities.App
import com.seljabali.network.networkModule
import io.objectbox.android.AndroidObjectBrowser
import org.koin.android.ext.android.inject
import java.lang.ref.WeakReference
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TemplateApplication: Application() {

    companion object {
        lateinit var instance: WeakReference<Context>
    }

    private val modules = listOf(
        networkModule,
        utilsModule
    )
    private val gson: Gson by inject()

    override fun onCreate() {
        super.onCreate()
        instance = WeakReference(applicationContext)
        setupLibraries()
    }

    private fun setupLibraries() {
        ObjectBox.init(this)
        if (App.isInDebugMode()) {
            AndroidObjectBrowser(ObjectBox.get()).start(this)
        }
        Logger.addLogAdapter(AndroidLogAdapter())
        AndroidThreeTen.init(this)
        startKoin {
            androidLogger()
            androidContext(this@TemplateApplication)
            modules(modules)
        }
        Hawk.init(this)
            .setParser(GsonParser(gson))
            .build()
    }
}