package com.seljabali.core.utilities

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.annotation.StyleRes

fun TextView.getTextValue(): String = text.toString()

fun TextView.isEmpty(): Boolean = getTextValue().isEmpty()

fun TextView.isNotEmpty(): Boolean = !isEmpty()

fun TextView.isBlank(): Boolean = getTextValue().isBlank()

fun TextView.isNotBlank(): Boolean = !isBlank()

fun TextView.trimmed(): String = getTextValue().trim()

fun TextView.getTextLength(): Int = getTextValue().length

fun TextView.setLink(text: String, linkAddress: String) {
    if (linkAddress.isEmpty()) {
        return
    }
    val value = "<html> <a href=\"$linkAddress\">$text</a> </html>"
    this.text = Html.fromHtml(value)
    movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.setLink(linkAddress: String) {
    setLink(getTextValue(), linkAddress)
}

fun TextView.appendText(text: String) {
    val newTextValue = getTextValue() + text
    this.text = newTextValue
}

fun TextView.setUnderlined() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun TextView.getSp(): Int = (textSize / resources.displayMetrics.scaledDensity).toInt()

fun TextView.setTextAppearanceStyle(context: Context, @StyleRes resId: Int) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
        setTextAppearance(context, resId)
    } else {
        setTextAppearance(resId)
    }
}