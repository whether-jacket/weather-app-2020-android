package com.seljabali.core.activityfragment.toolbar

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.logger.Logger
import com.seljabali.core.activityfragment.backpresslistener.OnBackPressListener
import com.seljabali.core.activityfragment.onVisibleFragment.OnVisibleFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseToolbarFragment : Fragment(), OnBackPressListener, ToolbarApi,
    OnVisibleFragment {

    protected val baseActivity get() = activity as BaseToolbarActivity
    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Logger.v("${getDisplayTag()} Fragment Attached")
        baseActivity.addBackPressListener(this)
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
        baseActivity.removeBackPressListener(this)
    }

    override fun shouldStopBackPress(): Boolean {
        // for subclasses to override
        return false
    }

    override fun setToolbarTitle(title: String) {
        baseActivity.setToolbarTitle(title)
    }

    override fun setToolbarSubtitle(subtitle: String) {
        baseActivity.setToolbarSubtitle(subtitle)
    }

    override fun showBackButton(show: Boolean) {
        baseActivity.showBackButton(show)
    }

    override fun onVisible() {
        Logger.v("${getDisplayTag()} Fragment Visible")
        val toolbarTitle = getToolbarTitle()
        if (toolbarTitle.isNotEmpty()) setToolbarTitle(toolbarTitle)
    }

    open fun getToolbarTitle(): String {
        // for subclasses to override
        return ""
    }

    fun getDisplayTag(): String = javaClass.simpleName

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }
}