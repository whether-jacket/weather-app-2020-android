@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.text.TextUtils

// Pick Contact
fun Intents.Companion.getPickContact(): Intent = getPickContact(null)

fun Intents.Companion.getPickContactWithPhone(): Intent = getPickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)

fun Intents.Companion.getPickContactWithEmail(): Intent = getPickContact(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE)

fun Intents.Companion.getPickContact(scope: String?): Intent =
        Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts")).apply {
            if (!scope.isNullOrBlank()) {
                type = scope
            }
        }

// Dial
fun Intents.Companion.getDialNumber(phoneNumber: String?): Intent =
        if (phoneNumber == null || phoneNumber.trim().isEmpty())
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:"))
        else
            Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + phoneNumber.replace(" ", "")))

// SMS
fun Intents.Companion.getSms(): Intent = getSms(null, null as Array<String>?)

fun Intents.Companion.getSms(phoneNumber: String): Intent = getSms(null, arrayOf(phoneNumber))

fun Intents.Companion.getSms(phoneNumbers: Array<String>): Intent = getSms(null, phoneNumbers)

fun Intents.Companion.getSmsWithBody(body: String): Intent = getSms(body, null as Array<String>?)

fun Intents.Companion.getSms(body: String, phoneNumber: String): Intent = getSms(body, arrayOf(phoneNumber))

fun Intents.Companion.getSms(body: String?, phoneNumbers: ArrayList<String>?): Intent = getSms(body, phoneNumbers?.toTypedArray())

fun Intents.Companion.getSms(body: String?, phoneNumbers: Array<String>?): Intent {
    val smsUri: Uri = if (phoneNumbers == null || phoneNumbers.isEmpty()) {
        Uri.parse("smsto:")
    } else {
        Uri.parse("smsto:" + Uri.encode(TextUtils.join(",", phoneNumbers)))
    }
    val intent = Intent(Intent.ACTION_VIEW, smsUri)
    if (body != null) {
        intent.putExtra("sms_body", body)
    }
    return intent
}