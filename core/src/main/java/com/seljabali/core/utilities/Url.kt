package com.seljabali.core.utilities

import android.net.Uri
import java.net.URLDecoder
import java.net.URLEncoder

object Url {

    private const val UTF_8 = "UTF-8"

    @JvmStatic
    fun encode(url: String): String = URLEncoder.encode(url, UTF_8)

    @JvmStatic
    fun decode(url: String): String = URLDecoder.decode(url, UTF_8)

    @JvmStatic
    fun parse(url: String): Uri? {
        if (url.isEmpty()) {
            return null
        }
        var uri: Uri? = null
        try {
            uri = Uri.parse(url)
        } catch (e: Exception) {
        }
        return uri
    }

    @JvmStatic
    fun getParam(url: String, key: String): String? {
        val uri = parse(url) ?: return null
        var value: String? = null
        try {
            value = uri.getQueryParameter(key)
        } catch (e: Exception) {
        }
        return value
    }

}