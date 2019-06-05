package com.seljabali.templateapplication

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class TemplateApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupLibraries()
    }

    private fun setupLibraries() {
        ObjectBox.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}