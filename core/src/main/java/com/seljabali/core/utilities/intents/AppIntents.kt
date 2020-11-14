@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.seljabali.core.utilities.App

fun Intents.Companion.getLaunchApp(context: Context, packageName: String): Intent? =
    context.packageManager.getLaunchIntentForPackage(packageName)

// TODO: Update to newer implementation
fun Intents.Companion.createShortCut(context: Context, launcherActivity: Activity, text: String?, drawable: Int?) {
    val shortcutIntent = Intent(context.applicationContext, launcherActivity.javaClass)
    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

    val addIntent = Intent()
    addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent)
    if (text == null) {
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, App.getName(context))
    } else {
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, text)
    }
    if (drawable != null) {
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context.applicationContext, drawable))
    }
    addIntent.putExtra("duplicate", false)
    addIntent.action = "com.android.launcher.action.INSTALL_SHORTCUT"
    context.applicationContext.sendBroadcast(addIntent)
}