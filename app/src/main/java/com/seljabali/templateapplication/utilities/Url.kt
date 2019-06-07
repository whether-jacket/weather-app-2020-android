package com.seljabali.templateapplication.utilities

import android.net.Uri
import android.text.TextUtils
import java.net.URLEncoder

object Url {

    @JvmStatic
    fun encode(url: String): String {
        if (TextUtils.isEmpty(url)) {
            return url
        }
        var encodedURL = ""
        val temp = url.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val length = temp.size
        for (index in 0 until length) {
            try {
                temp[index] = URLEncoder.encode(temp[index], "UTF-8")
                temp[index] = temp[index].replace("+", "%20")
            } catch (e: Exception) {
                return url
            }
            encodedURL += temp[index]
            if (index < length - 1) {
                encodedURL += "/"
            }
        }
        return encodedURL
    }

    @JvmStatic
    fun parse(url: String): Uri? {
        if (TextUtils.isEmpty(url)) {
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