package com.seljabali.core.activityfragment.nontoolbar

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.v("${getDisplayTag()} Fragment Attached")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.v("${getDisplayTag()} View Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getDisplayTag()} Fragment Paused")
        clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()
        Logger.v("${getDisplayTag()} Fragment Resumed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v("${getDisplayTag()} View Destroyed")
    }

    fun getDisplayTag(): String = javaClass.simpleName

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}