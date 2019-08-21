package com.seljabali.templateapplication

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.hawk.GsonParser
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.seljabali.core.network.ApiServiceBuilder
import com.seljabali.core.utilities.App
import io.objectbox.android.AndroidObjectBrowser
import java.lang.ref.WeakReference

class TemplateApplication: Application() {

    companion object {
        lateinit var instance: WeakReference<Context>
    }

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
        Hawk.init(this)
            .setParser(GsonParser(ApiServiceBuilder.gson))
            .build()
    }
}