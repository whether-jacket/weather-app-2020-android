package com.seljabali.core.activityfragment.nontoolbar

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Logger.v("${getTag()} Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getTag()} Paused")
        clearSubscriptions()
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

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }

    protected fun findFragmentByTag(tag: String): BaseFragment? {
        var fragmentCandidate = supportFragmentManager.findFragmentByTag(tag) as? BaseFragment
        if (fragmentCandidate != null) return fragmentCandidate
        for (fragmentStack in supportFragmentManager.fragments) {
            fragmentCandidate = fragmentStack as BaseFragment
            if (fragmentCandidate.getDisplayTag() == tag) {
                return fragmentCandidate
            }
        }
        return null
    }

    private fun getTag(): String = javaClass.simpleName
}