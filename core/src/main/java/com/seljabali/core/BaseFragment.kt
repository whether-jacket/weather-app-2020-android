package com.seljabali.core

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment : Fragment(), BaseActivity.OnBackClickListener {

    protected val baseActivity get() = activity as BaseActivity
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.v("${getDisplayTag()} Fragment Attached")
        baseActivity.addBackClickListener(this)
    }

    override fun onBackClick(): Boolean {
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Logger.v("${getDisplayTag()} View Created")
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getDisplayTag()} Fragment Paused")
        compositeDisposable.clear()
    }

    override fun onResume() {
        super.onResume()
        Logger.v("${getDisplayTag()} Fragment Resumed")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Logger.v("${getDisplayTag()} View Destroyed")
        baseActivity.removeBackClickListener(this)
    }

    override fun getContext(): Context = activity as Context

    open fun onVisible() {
        Logger.v("${getDisplayTag()} Fragment Visible")
        val toolbarTitle = getToolbarTitle()
        if (toolbarTitle.isNotEmpty()) setToolBarTitle(toolbarTitle)
    }

    open fun getToolbarTitle(): String {
        // for subclasses to override
        return ""
    }

    fun getDisplayTag(): String = javaClass.simpleName

    protected fun setToolBarTitle(title: String) {
        baseActivity.supportActionBar?.title = title
    }

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}