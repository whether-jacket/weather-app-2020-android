@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

fun Intents.Companion.isIntentAvailable(context: Context, intent: Intent?): Boolean {
    val packageManager = context.packageManager
    val list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return list.size > 0
}

fun Intents.Companion.isIntentAvailable(context: Context, packageName: String): Boolean {
    return try {
        context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Intents.Companion.isIntentAvailable(context: Context, action: String, uri: Uri?, mimeType: String?): Boolean {
    val intent = if (uri != null) Intent(action, uri) else Intent(action)
    if (mimeType != null) {
        intent.type = mimeType
    }
    val list = context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return list.isNotEmpty()
}

fun Intents.Companion.isIntentAvailable(context: Context, action: String, mimeType: String?): Boolean {
    val intent = Intent(action)
    if (mimeType != null) {
        intent.type = mimeType
    }
    val list = context.packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
    return list.isNotEmpty()
}