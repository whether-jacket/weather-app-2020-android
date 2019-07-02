package com.seljabali.templateapplication

import android.app.Application
import android.content.Context
import com.jakewharton.threetenabp.AndroidThreeTen
import com.orhanobut.hawk.GsonParser
import com.orhanobut.hawk.Hawk
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.seljabali.templateapplication.models.GithubProfile
import com.seljabali.templateapplication.network.GithubService
import com.seljabali.templateapplication.network.ServiceGenerator

class TemplateApplication: Application() {

    companion object {
        lateinit var appContext: Context
        lateinit var serviceGenerator: ServiceGenerator<GithubService>
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        setupLibraries()
    }

    private fun setupLibraries() {
        ObjectBox.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
        AndroidThreeTen.init(this)
        serviceGenerator = ServiceGenerator("www.github.com", GithubService::class.java)
        Hawk.init(this)
            .setParser(GsonParser(ServiceGenerator.gson))
            .build()
    }
}