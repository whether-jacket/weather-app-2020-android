package com.seljabali.templateapplication

import android.app.Application

class TemplateApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        setupLibraries()
    }

    private fun setupLibraries() {
        ObjectBox.init(this)
    }
}