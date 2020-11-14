@file:JvmName("IntentsUtil")
@file:JvmMultifileClass

package com.seljabali.core.utilities.intents

import android.annotation.TargetApi
import android.content.Intent
import android.provider.Settings

private const val DEFAULT_FLAG = Intent.FLAG_ACTIVITY_NO_HISTORY

fun Intents.Companion.getLocationServicesSettings(): Intent {
    val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getSettings(): Intent {
    val intent = Intent(Settings.ACTION_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getApnSettings(): Intent {
    val intent = Intent(Settings.ACTION_APN_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getWirelessSettings(): Intent {
    val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getAirplaneModeSettings(): Intent {
    val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getAccessibilitySettings(): Intent {
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

@TargetApi(21)
fun Intents.Companion.getUsageSettings(): Intent {
    val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getSecuritySettings(): Intent {
    val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}

fun Intents.Companion.getPrivacySettings(): Intent {
    val intent = Intent(Settings.ACTION_PRIVACY_SETTINGS)
    intent.addFlags(DEFAULT_FLAG)
    return intent
}