package com.seljabali.templateapplication.utilities

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

object App {

    @JvmStatic
    fun getPakageName(context: Context): String =
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).packageName
        } catch (e: Exception) {
            ""
        }

    @JvmStatic
    fun getVersionName(context: Context): String =
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: Exception) {
            ""
        }

    @RequiresApi(Build.VERSION_CODES.P)
    @JvmStatic
    fun getVersionCode(context: Context): Long =
        try {
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode
        } catch (e: Exception) {
            0L
        }
}