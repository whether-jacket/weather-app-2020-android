package com.seljabali.templateapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable

abstract class BaseActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Logger.v("${getTag()} Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getTag()} Paused")
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
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