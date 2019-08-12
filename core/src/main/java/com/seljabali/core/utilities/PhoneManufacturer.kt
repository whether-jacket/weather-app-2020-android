package com.seljabali.core.utilities

import android.os.Build

object PhoneManufacturer {

    private const val SAMSUNG_MANUFACTURER = "samsung"
    private const val SAMSUNG_S3 = "d2"
    private val SAMSUNG_S6_NAMES = arrayOf("SM-G920F", "SM-G920I", "SM-G920w8")

    @JvmStatic
    fun getName():String = Build.MANUFACTURER

    @JvmStatic
    fun getBrand():String = Build.BRAND

    @JvmStatic
    fun getModel():String = Build.MODEL

    @JvmStatic
    fun isSamsungDevice(): Boolean = SAMSUNG_MANUFACTURER == getName().toLowerCase()

    @JvmStatic
    fun isSamsungS3(): Boolean = Build.DEVICE.startsWith(SAMSUNG_S3)

    @JvmStatic
    fun isSamsungS6(): Boolean = SAMSUNG_S6_NAMES.any { getModel().contains(it) }
}