@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Intent
import android.net.Uri
import com.seljabali.core.utilities.MimeTypes

fun Intents.Companion.getInboxOpen(): Intent =
        Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_APP_EMAIL)
        }

fun Intents.Companion.getEmailSend(address: String, subject: String?): Intent? {
    if (address.isEmpty()) {
        return null
    }
    var url = "mailto: $address"
    if (subject != null && subject.isNotEmpty()) {
        url += "?subject=" + Uri.encode(subject)
    }
    return getEmailSend(url)
}

fun Intents.Companion.getEmailSend(url: String): Intent =
        Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(url)
        }

fun Intents.Companion.getShareEmail(address: String?, subject: String?, body: String?): Intent =
        getShareEmail(address, subject, body, null)

fun Intents.Companion.getShareEmail(address: String?, subject: String?, body: String?, attachment: Uri?): Intent =
        getShareEmail(if (address == null) null else arrayOf(address), subject, body, attachment)

fun Intents.Companion.getShareEmail(addresses: ArrayList<String>?, subject: String?, body: String?, attachment: Uri?): Intent =
        getShareEmail(addresses!!.toTypedArray(), subject, body, attachment)

fun Intents.Companion.getShareEmail(addresses: Array<String>?, subject: String?, body: String?, attachment: Uri?): Intent =
        ShareIntentBuilder()
                .withAddresses(addresses)
                .withSubject(subject)
                .withText(body)
                .withAttachment(attachment)
                .withExplicitMimeType(MimeTypes.MESSAGE_EMAIL.id)
                .build()