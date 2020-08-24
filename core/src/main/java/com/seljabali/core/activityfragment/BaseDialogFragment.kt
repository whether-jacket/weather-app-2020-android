package com.seljabali.core.activityfragment

import android.app.Dialog
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.view.WindowManager
import android.view.Gravity
import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseDialogFragment : DialogFragment() {

    companion object {
        private const val screenWidthPercentage: Float = 0.80f
    }

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isShowingTitle()) {
            setStyle(STYLE_NORMAL, getStyle())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!isShowingTitle()) {
            dialog?.requestWindowFeature(STYLE_NO_TITLE)
        }
        dialog?.setCanceledOnTouchOutside(isCancelableDialog())
        isCancelable = isCancelableDialog()
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog: Dialog = super.onCreateDialog(savedInstanceState)
        dialog.setTitle(getTitle())
        return dialog
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

    protected fun subscribe(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    protected fun clearSubscriptions() {
        compositeDisposable.clear()
    }

    protected open fun isShowingTitle(): Boolean {
        return false
    }

    protected open fun getTitle(): String {
        return ""
    }

    protected open fun isCancelableDialog(): Boolean {
        return true
    }

    @StyleRes protected open fun getStyle(): Int {
        return android.R.style.Theme_Dialog
    }
}