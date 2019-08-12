package com.seljabali.core.utilities

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build.VERSION_CODES.M
import androidx.annotation.IntRange
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

object Permission {

    /**
     *  Has Permission
     */
    @JvmStatic
    fun isNeeded(): Boolean = OsVersion.isAtLeast(M)

    @JvmStatic
    fun isNotNeeded(): Boolean = !isNeeded()

    @JvmStatic
    fun isGranted(context: Context, permission: String): Boolean = isNotNeeded() || ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    @JvmStatic
    fun isNotGranted(context: Context, permission: String): Boolean = !isGranted(context, permission)

    /**
     *  Request Permission
     */
    @JvmStatic
    fun request(activity: Activity, permission: String, @IntRange(from = 0, to = 65535) requestCode: Int) {
        ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
    }

    @JvmStatic
    fun request(activity: Activity, permissions: Array<String>, @IntRange(from = 0, to = 65535) requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    @JvmStatic
    fun request(fragment: Fragment, permission: String, @IntRange(from = 0, to = 65535) requestCode: Int) {
        fragment.requestPermissions(arrayOf(permission), requestCode)
    }

    @JvmStatic
    fun request(fragment: Fragment, permissions: Array<String>, @IntRange(from = 0, to = 65535) requestCode: Int) {
        fragment.requestPermissions(permissions, requestCode)
    }

    /**
     *  Granted Permission
     */
    @JvmStatic
    fun wasGranted(grantResult: Int): Boolean = grantResult == PackageManager.PERMISSION_GRANTED

    @JvmStatic
    fun wasGranted(grantResults: IntArray): Boolean = wasGranted(grantResults[0])
}