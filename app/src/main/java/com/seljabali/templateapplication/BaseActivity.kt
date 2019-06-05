package com.seljabali.templateapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Logger.v("${getTag()} Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getTag()} Paused")
    }

    override fun onStop() {
        super.onStop()
        Logger.v("${getTag()} Stopped")
    }

    override fun onResume() {
        super.onResume()
        Logger.v("${getTag()} Resumed")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.v("${getTag()} Destroyed")
    }

    private fun getTag(): String = javaClass.simpleName
}