package com.seljabali.network.interceptors

import com.seljabali.network.ContentTypes
import com.seljabali.network.RequestHeaders
import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor(
    private val authToken: String = "",
    private val cookie: String = ""
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder().apply {
                    if (authToken.isNotEmpty()) {
                        header(RequestHeaders.AUTH.id, authToken)
                    }
                    if (cookie.isNotEmpty()) {
                        header(RequestHeaders.COOKIE.id, cookie)
                    }
                }
                .header(RequestHeaders.CONTENT_TYPE.id, ContentTypes.JSON.id)
                .build()
        )
    }
}
