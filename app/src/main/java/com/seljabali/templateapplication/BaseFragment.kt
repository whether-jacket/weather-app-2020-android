package com.seljabali.templateapplication

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.v("${getDisplayTag()} View Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getDisplayTag()} Fragment Paused")
        compositeDisposable.clear()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v("${getDisplayTag()} View Destroyed")
    }

    override fun getContext(): Context = activity as Context

    private fun getDisplayTag(): String = javaClass.simpleName

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}