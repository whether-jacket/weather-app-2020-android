package com.seljabali.core.utilities

import android.app.NotificationChannelGroup
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.seljabali.core.R

enum class NotificationChannelGroups(
    val id: String,
    @StringRes val groupName: Int
) {
    CHAT("chats_group", R.string.name);

    @RequiresApi(Build.VERSION_CODES.O)
    fun newInstance(context: Context) =
        NotificationChannelGroup(id, context.getString(groupName))
}