package com.seljabali.core.activityfragment

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment : DialogFragment() {

    companion object {
        private const val screenWidthPercentage: Float = 0.80f
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!isShowingTitle()) {
            dialog?.requestWindowFeature(STYLE_NO_TITLE)
        }
        dialog?.setCanceledOnTouchOutside(isCancelableDialog())
        isCancelable = isCancelableDialog()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        clearSubscriptions()
    }

    override fun onResume() {
        super.onResume()

        val window = dialog?.window ?: return
        val size = Point().apply {
            val display = window.windowManager.defaultDisplay
            display.getSize(this)
        }

        val width = size.x
        window.setLayout((width * screenWidthPercentage).toInt(), WindowManager.LayoutParams.WRAP_CONTENT)
        window.setGravity(Gravity.CENTER)
    }

    override fun getContext(): Context = activity as Context

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }

    protected fun isShowingTitle(): Boolean {
        return false
    }

    protected fun isCancelableDialog(): Boolean {
        return true
    }
}