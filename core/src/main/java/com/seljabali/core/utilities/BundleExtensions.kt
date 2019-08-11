package com.seljabali.core.utilities

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

fun Bundle.put(key: String, value: String) {
    putString(key, value)
}

fun Bundle.put(key: String, value: Array<String>) {
    putStringArray(key, value)
}

fun Bundle.put(key: String, value: Boolean) {
    putBoolean(key, value)
}

fun Bundle.put(key: String, value: BooleanArray) {
    putBooleanArray(key, value)
}

fun Bundle.put(key: String, value: Bundle) {
    putBundle(key, value)
}

fun Bundle.put(key: String, value: Byte) {
    putByte(key, value)
}

fun Bundle.put(key: String, value: ByteArray) {
    putByteArray(key, value)
}

fun Bundle.put(key: String, value: Char) {
    putChar(key, value)
}

fun Bundle.put(key: String, value: CharArray) {
    putCharArray(key, value)
}

fun Bundle.put(key: String, value: CharSequence) {
    putCharSequence(key, value)
}

fun Bundle.put(key: String, value: Array<CharSequence>) {
    putCharSequenceArray(key, value)
}

fun Bundle.put(key: String, value: Double) {
    putDouble(key, value)
}

fun Bundle.put(key: String, value: DoubleArray) {
    putDoubleArray(key, value)
}

fun Bundle.put(key: String, value: Float) {
    putFloat(key, value)
}

fun Bundle.put(key: String, value: FloatArray) {
    putFloatArray(key, value)
}

fun Bundle.put(key: String, value: Int) {
    putInt(key, value)
}

fun Bundle.put(key: String, value: IntArray) {
    putIntArray(key, value)
}

fun Bundle.put(key: String, value: Long) {
    putLong(key, value)
}

fun Bundle.put(key: String, value: LongArray) {
    putLongArray(key, value)
}

fun Bundle.put(key: String, value: Short) {
    putShort(key, value)
}

fun Bundle.put(key: String, value: ShortArray) {
    putShortArray(key, value)
}

fun Bundle.put(key: String, value: Serializable) {
    putSerializable(key, value)
}

fun Bundle.put(key: String, value: Parcelable) {
    putParcelable(key, value)
}

fun Bundle.put(key: String, value: Array<Parcelable>) {
    putParcelableArray(key, value)
}

fun Bundle.put(bundle: Bundle) {
    this.putAll(bundle)
}

fun Bundle.putObject(key: String, `object`: Any) {
    when (`object`) {
        is Bundle -> putBundle(key, `object`)
        is String -> putString(key, `object`)
        is Int -> putInt(key, `object`)
        is Float -> putFloat(key, `object`)
        is Double -> putDouble(key, `object`)
        is Parcelable -> putParcelable(key, `object`)
        is Serializable -> putSerializable(key, `object`)
    }
}