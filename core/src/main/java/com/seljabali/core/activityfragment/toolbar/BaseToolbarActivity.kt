package com.seljabali.core.activityfragment.toolbar

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.orhanobut.logger.Logger
import com.seljabali.core.activityfragment.backpresslistener.BackPressButtonHolder
import com.seljabali.core.activityfragment.backpresslistener.OnBackPressListener
import com.seljabali.core.activityfragment.onVisibleFragment.OnVisibleFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseToolbarActivity : AppCompatActivity(),
    FragmentManager.OnBackStackChangedListener,
    BackPressButtonHolder,
    ToolbarApi {

    private val compositeDisposable = CompositeDisposable()
    private val backClickListenersList = ArrayList<WeakReference<OnBackPressListener>>()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Logger.v("${getTag()} Created")
    }

    override fun onBackPressed() {
        if (!anyFragmentStopBackPress()) {
            super.onBackPressed()
        }
    }

    override fun onPause() {
        super.onPause()
        Logger.v("${getTag()} Paused")
        compositeDisposable.clear()
    }

    override fun onStop() {
        super.onStop()
        Logger.v("${getTag()} Stopped")
    }

    override fun onResume() {
        super.onResume()
        Logger.v("${getTag()} Resumed")
        // since onCreate doesn't register listener in subclasses we do it onResume
        // since onResume can occur more than once, we remove it in case it already exists
        supportFragmentManager.removeOnBackStackChangedListener(this)
        supportFragmentManager.addOnBackStackChangedListener(this)
    }

    override fun onBackStackChanged() {
        val topFragment = supportFragmentManager.fragments.last() as? OnVisibleFragment
        topFragment?.onVisible()

        val supportActionBar = supportActionBar ?: return
        val isAtHomePage = supportFragmentManager.backStackEntryCount < 1
        supportActionBar.setDisplayHomeAsUpEnabled(!isAtHomePage)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.v("${getTag()} Destroyed")
    }

    override fun addBackPressListener(onBackPressListener: OnBackPressListener) {
        backClickListenersList.add(WeakReference(onBackPressListener))
    }

    override fun removeBackPressListener(onBackPressListener: OnBackPressListener) {
        val iterator = backClickListenersList.iterator()
        while (iterator.hasNext()) {
            val weakRef = iterator.next()
            if (weakRef.get() === onBackPressListener) {
                iterator.remove()
            }
        }
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setToolbarSubtitle(subtitle: String) {
        supportActionBar?.subtitle = subtitle
    }

    override fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
    }

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }

    protected fun findFragmentByTag(tag: String): BaseToolbarFragment? {
        var fragmentCandidate =
            supportFragmentManager.findFragmentByTag(tag) as? BaseToolbarFragment
        if (fragmentCandidate != null) return fragmentCandidate
        for (fragmentStack in supportFragmentManager.fragments) {
            fragmentCandidate = fragmentStack as BaseToolbarFragment
            if (fragmentCandidate.getDisplayTag() == tag) {
                return fragmentCandidate
            }
        }
        return null
    }

    private fun getTag(): String = javaClass.simpleName

    private fun anyFragmentStopBackPress(): Boolean {
        if (supportFragmentManager.fragments.isEmpty()) return false
        val topFragment =
            supportFragmentManager.fragments.last() as? OnBackPressListener ?: return false
        var stopped = false
        for (weakRef in backClickListenersList) {
            val onBackClickListener = weakRef.get() ?: continue
            if (topFragment != onBackClickListener) continue
            val doesFragmentStopBackPress = onBackClickListener.shouldStopBackPress()
            stopped = stopped or doesFragmentStopBackPress
        }
        return stopped
    }
}