package com.seljabali.core.utilities

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity

object Keyboard {

    @JvmStatic
    fun hide(activity: Activity) {
        val view = activity.currentFocus ?: return
        hide(activity, view)
    }

    @JvmStatic
    fun hide(activity: FragmentActivity) {
        val view = activity.currentFocus ?: return
        hide(activity, view)
    }

    @JvmStatic
    fun hide(context: Context, view: View) {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    @JvmStatic
    fun show(context: Context, view: View) {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(
            InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY
        )
        inputManager.showSoftInput(view, 0)
    }
}