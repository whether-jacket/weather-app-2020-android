package com.seljabali.core

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.lang.ref.WeakReference

abstract class BaseActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private val compositeDisposable = CompositeDisposable()
    private val backClickListenersList = ArrayList<WeakReference<OnBackClickListener>>()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        Logger.v("${getTag()} Created")
    }

    override fun onBackPressed() {
        if (!anyFragmentBackPressIntercept()) {
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
        val topFragment = supportFragmentManager.fragments.last() as? BaseFragment
        topFragment?.onVisible()
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.v("${getTag()} Destroyed")
    }

    fun addBackClickListener(onBackClickListener: OnBackClickListener) {
        backClickListenersList.add(WeakReference(onBackClickListener))
    }

    fun removeBackClickListener(onBackClickListener: OnBackClickListener) {
        val iterator = backClickListenersList.iterator()
        while (iterator.hasNext()) {
            val weakRef = iterator.next()
            if (weakRef.get() === onBackClickListener) {
                iterator.remove()
            }
        }
    }

    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setToolbarSubtitle(subtitle: String) {
        supportActionBar?.subtitle = subtitle
    }

    fun showBackButton(show: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(show)
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

    private fun anyFragmentBackPressIntercept(): Boolean {
        val topFragment = supportFragmentManager.fragments.last() as? BaseFragment ?: return false
        var intercepted = false
        for (weakRef in backClickListenersList) {
            val onBackClickListener = weakRef.get() ?: continue
            if (topFragment != onBackClickListener) continue
            val didFragmentIntercept = onBackClickListener.onBackClick()
            intercepted = intercepted or didFragmentIntercept
        }
        return intercepted
    }

    interface OnBackClickListener {
        fun onBackClick(): Boolean
    }
}