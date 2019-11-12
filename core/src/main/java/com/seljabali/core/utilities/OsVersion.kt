package com.seljabali.core.utilities

import android.os.Build

object OsVersion {

    @JvmStatic
    fun isLessThan(apiVersion: Int): Boolean = apiNumber() < apiVersion

    @JvmStatic
    fun isAtMost(apiVersion: Int): Boolean = apiNumber() <= apiVersion

    @JvmStatic
    fun isEqual(apiVersion: Int): Boolean = apiNumber() == apiVersion

    @JvmStatic
    fun isNotEqual(apiVersion: Int): Boolean = !isEqual(apiVersion)

    @JvmStatic
    fun isAtLeast(apiVersion: Int): Boolean = apiNumber() >= apiVersion

    @JvmStatic
    fun isGreaterThan(apiVersion: Int): Boolean = apiNumber() > apiVersion

    @JvmStatic
    fun apiNumber(): Int = Build.VERSION.SDK_INT // 26

    @JvmStatic
    fun releaseNumber(): String = Build.VERSION.RELEASE // 8

    @JvmStatic
    fun releaseName(): String { // O
        val fields = Build.VERSION_CODES::class.java.fields
        var fieldValue = -1
        for (field in fields) {
            val fieldName = field.name
            try {
                fieldValue = field.getInt(Any())
            } catch (e: IllegalArgumentException) {
            } catch (e: IllegalAccessException) {
            } catch (e: NullPointerException) {
            }
            if (fieldValue == apiNumber()) {
                return fieldName
            }
        }
        return ""
    }

}