package com.seljabali.templateapplication.network.interceptors

import com.seljabali.templateapplication.network.ResponseHeader
import okhttp3.Interceptor
import okhttp3.Response

class ResponseCookiesInterceptor(private val listener: CookieListener) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        if (originalResponse.headers(ResponseHeader.SET_COOKIE.id).isNotEmpty()) {
            val cookies = HashSet<String>()
            for (header in originalResponse.headers(ResponseHeader.SET_COOKIE.id)) {
                cookies.add(header)
            }
            listener.saveCookies(cookies)
        }
        return originalResponse
    }

    interface CookieListener {
        fun saveCookies(hashSet: HashSet<String>)
    }
}