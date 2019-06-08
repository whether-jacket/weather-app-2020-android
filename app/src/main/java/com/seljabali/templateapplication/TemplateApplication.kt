package com.seljabali.templateapplication

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class TemplateApplication: Application() {

    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        setupLibraries()
    }

    private fun setupLibraries() {
        ObjectBox.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}