package com.seljabali.templateapplication.utilities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object Clipboard {

    @JvmStatic
    fun copy(context: Context, @StringRes textId: Int) {
        copy(context, context.getString(textId))
    }

    @JvmStatic
    fun copy(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.primaryClip = clip
    }

    @JvmStatic
    fun copyAndToast(context: Context, text: String, @StringRes toastText: Int) {
        copyAndToast(context, text, context.getString(toastText))
    }

    @JvmStatic
    fun copyAndToast(context: Context, text: String, toastText: String) {
        copy(context, text)
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
    }
}