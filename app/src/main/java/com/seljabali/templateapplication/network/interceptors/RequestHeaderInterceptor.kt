package com.seljabali.templateapplication.network.interceptors

import com.seljabali.templateapplication.network.ContentType
import com.seljabali.templateapplication.network.RequestHeader
import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor(private val authToken: String, private val cookie: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder().apply {
                    if (authToken.isNotEmpty()) {
                        header(RequestHeader.AUTH.id, authToken)
                    }
                    if (cookie.isNotEmpty()) {
                        header(RequestHeader.COOKIE.id, cookie)
                    }
                }
                .header(RequestHeader.CONTENT_TYPE.id, ContentType.JSON.id)
                .header("xctg-type", "mobile")
                .build()
        )
    }
}