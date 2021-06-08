package com.seljabali.core.utilities

import android.content.Context
import android.os.Build.VERSION_CODES.P
import com.seljabali.core.BuildConfig

object App {

    @JvmStatic
    fun getName(context: Context): String {
        val applicationInfo = context.applicationInfo
        val stringId = applicationInfo.labelRes
        return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else context.getString(stringId)
    }

    @JvmStatic
    fun getPackageName(context: Context): String =
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
                0L
                // TODO: Restore values to build
//                BuildConfig.VERSION_CODE.toLong()
            }
        } catch (e: Exception) {
            0L
        }

    @JvmStatic
    fun isInDebugMode(): Boolean = BuildConfig.DEBUG

    @JvmStatic
    fun getBuildType(): String = BuildConfig.BUILD_TYPE

    // TODO: Restore values to build
//    @JvmStatic
//    fun getBuildFlavor(): String = BuildConfig.FLAVOR
}