package com.seljabali.templateapplication.utilities

import android.os.Build

object PhoneManufacturer {

    private const val SAMSUNG_MANUFACTURER = "samsung"
    private const val SAMSUNG_S3_DEVICE_COMMON_PREFIX = "d2"

    @JvmStatic
    fun isSamsungDevice(): Boolean = SAMSUNG_MANUFACTURER == Build.MANUFACTURER.toLowerCase()

    @JvmStatic
    fun isSamsungGalaxyS3(): Boolean = Build.DEVICE.startsWith(SAMSUNG_S3_DEVICE_COMMON_PREFIX)

    @JvmStatic
    fun isSamsungS6(): Boolean = Build.MODEL.contains("SM-G920F") || Build.MODEL.contains("SM-G920I") || Build.MODEL.contains("SM-G920w8")
}