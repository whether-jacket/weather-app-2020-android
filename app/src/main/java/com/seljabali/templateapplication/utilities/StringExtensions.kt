package com.seljabali.templateapplication.utilities

import java.util.regex.Pattern

fun String.isEmailValid(): Boolean =
    Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    ).matcher(this).matches()

fun String.isEmailInvalid(): Boolean = !isEmailValid()

fun String.getDomainOfEmail(): String = this.substring(this.indexOf("@") + 1, this.length)

fun String.compareInsensitiveOrder(rhs: String): Int = String.CASE_INSENSITIVE_ORDER.compare(this, rhs)

fun String.getWithoutHtmlTags(): String = if (isEmpty()) "" else android.text.Html.fromHtml(this).toString().trim { it <= ' ' }

fun String.getInBulletPoints(): String = if (isEmpty()) "" else "\u2022 " + this.replace("\n\n", "\n").replace("\n", "\n \u2022 ")

fun String.Companion.getListFlattened(delimiter: String, vararg stringList: String): String {
    if (stringList.isEmpty()) {
        return ""
    }
    val stringBuilder = StringBuilder()
    for (i in stringList.indices) {
        val currentString = stringList[i]
        if (currentString.isNotEmpty()) {
            stringBuilder.append(currentString)
            if (i + 1 != stringList.size && stringList[i + 1].isNotEmpty()) {
                stringBuilder.append(delimiter)
            }
        }
    }
    return stringBuilder.toString()
}

fun String.Companion.areAllEmpty(vararg strings: String): Boolean = strings.all { it.isEmpty() }

fun String.Companion.areAllNotEmpty(vararg strings: String): Boolean = strings.all { it.isNotEmpty() }

fun String.Companion.areAnyEmpty(vararg strings: String): Boolean = strings.any { it.isEmpty() }

fun String.Companion.areAnyNotEmpty(vararg strings: String): Boolean = strings.any { it.isNotEmpty() }