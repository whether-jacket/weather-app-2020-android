package com.seljabali.templateapplication.utilities

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object Clipboard {

    @JvmStatic
    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label", text)
        clipboard.primaryClip = clip
    }
}