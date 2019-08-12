package com.seljabali.core.utilities

import android.os.Build

object OsVersion {

    @JvmStatic
    fun isLessThan(version: Int): Boolean = Build.VERSION.SDK_INT < version

    @JvmStatic
    fun isAtMost(version: Int): Boolean = Build.VERSION.SDK_INT <= version

    @JvmStatic
    fun isEqual(version: Int): Boolean = Build.VERSION.SDK_INT == version

    @JvmStatic
    fun isNotEqual(version: Int): Boolean = !isEqual(version)

    @JvmStatic
    fun isAtLeast(version: Int): Boolean = Build.VERSION.SDK_INT >= version

    @JvmStatic
    fun isGreaterThan(version: Int): Boolean = Build.VERSION.SDK_INT > version

}