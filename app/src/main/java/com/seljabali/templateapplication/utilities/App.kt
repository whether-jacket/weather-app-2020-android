package com.seljabali.templateapplication.utilities

import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.StringDef
import kotlin.annotation.Retention

object App {

    @StringDef(GOOGLE_MAPS, PLAY_STORE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class AppsOnPlayStore

    const val GOOGLE_MAPS = "com.google.android.apps.maps"
    const val PLAY_STORE = "com.android.vending"

    @JvmStatic
    fun isInstalled(context: Context, packageName: String): Boolean =
        try {
            context.packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }

    @JvmStatic
    fun isInstalled(context: Context, appOnPlayStore: AppsOnPlayStore): Boolean = isInstalled(context, appOnPlayStore)

    @JvmStatic
    fun isNotInstalled(context: Context, packageName: String): Boolean = !isInstalled(context, packageName)

    @JvmStatic
    fun isNotInstalled(context: Context, appOnPlayStore: AppsOnPlayStore): Boolean = isNotInstalled(context, appOnPlayStore)
}