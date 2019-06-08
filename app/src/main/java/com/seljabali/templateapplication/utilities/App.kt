package com.seljabali.templateapplication.utilities

import android.content.Context
import android.os.Build.VERSION_CODES.P
import com.seljabali.templateapplication.BuildConfig

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

    @JvmStatic
    fun getVersionCode(context: Context): Long =
        try {
            if (OsVersion.isAtLeast(P)) {
                context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode
            } else {
                BuildConfig.VERSION_CODE.toLong()
            }
        } catch (e: Exception) {
            0L
        }

    @JvmStatic
    fun isInDebugMode(): Boolean = BuildConfig.DEBUG
}