package com.seljabali.core.utilities

import android.app.ActivityManager
import android.content.Context

object Service {

    @JvmStatic
    fun isRunningInForeground(context: Context, serviceClass: Class<*>): Boolean = isRunning(context, serviceClass, filterForegroundOnly = true)

    @Suppress("DEPRECATION")
    @JvmStatic
    fun isRunning(context: Context, serviceClass: Class<*>, filterForegroundOnly: Boolean = false): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name != service.service.className) continue
            if (!filterForegroundOnly) return true
            return service.foreground
        }
        return false
    }
}