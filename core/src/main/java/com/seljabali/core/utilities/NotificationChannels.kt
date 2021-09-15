package com.seljabali.core.utilities

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.seljabali.core.R

enum class NotificationChannels(
    val channelId: String,
    @StringRes val channelName: Int,
    val channelImportance: Int,
    val enableLights: Boolean = false,
    val lockscreenVisibility: Int = Notification.VISIBILITY_PUBLIC
) {
    @RequiresApi(Build.VERSION_CODES.N)
    CHAT("chats_group", R.string.name, NotificationManager.IMPORTANCE_MIN);

    @RequiresApi(Build.VERSION_CODES.O)
    fun newInstance(context: Context) =
        NotificationChannel(channelId, context.getString(channelName), channelImportance).apply {
            enableLights(this@NotificationChannels.enableLights)
            lockscreenVisibility = this@NotificationChannels.lockscreenVisibility
        }

}