package com.seljabali.core.network

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seljabali.core.network.interceptors.RequestHeaderInterceptor
import com.seljabali.core.network.interceptors.ResponseCookiesInterceptor
import com.seljabali.core.utilities.areAnyNotEmpty
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

class ApiServiceBuilder<S>(
    private val serviceClass: Class<S>
) {

    companion object {
        const val DEFAULT_TIME_OUT_SECONDS: Long = 20L
        val gson: Gson = GsonBuilder()
//            .registerTypeHierarchyAdapter(ZonedDateTime::class.java.javaClass, ZonedDateTimeTypeAdapterFactory())
//            .registerTypeAdapter(ZonedDateTime::class.java.javaClass, ZonedDateTimeTypeAdapterFactory())
            .create()
    }

    private var loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = NONE }
    private var networkInterceptor: Interceptor? = null
    private var cookieResponseListener: ResponseCookiesInterceptor.CookieListener? = null
    private lateinit var apiBaseUrl: String
    private var authToken: String = ""
    private var cookie: String = ""
    private var timeOutDurationSeconds: Long = DEFAULT_TIME_OUT_SECONDS
    private var acceptAllCerts: Boolean = false

    fun withApiBaseUrl(baseUrl: String): ApiServiceBuilder<S> {
        this.apiBaseUrl = baseUrl
        return this
    }

    fun withHeaderAuthTokenRequests(authToken: String): ApiServiceBuilder<S> {
        this.authToken = authToken
        return this
    }

    fun withHeaderCookieRequests(cookie: String): ApiServiceBuilder<S> {
        this.cookie = cookie
        return this
    }

    fun withResponseCookieListener(listener: ResponseCookiesInterceptor.CookieListener): ApiServiceBuilder<S> {
        this.cookieResponseListener = listener
        return this
    }

    fun withTimeOutDuration(timeOutInSeconds: Long): ApiServiceBuilder<S> {
        this.timeOutDurationSeconds = timeOutInSeconds
        return this
    }

    fun withAllCertsAccepted(allCertsAccepted: Boolean): ApiServiceBuilder<S> {
        this.acceptAllCerts = allCertsAccepted
        return this
    }

    fun withLoggingInterceptorEnabled(loggingInterceptorEnabled: Boolean): ApiServiceBuilder<S> {
        loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (loggingInterceptorEnabled) BODY else NONE
        }
        return this
    }

    fun withNetworkInterceptor(networkInterceptor: Interceptor?): ApiServiceBuilder<S> {
        this.networkInterceptor = networkInterceptor
        return this
    }

    fun build(): S {
        val httpClient: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            if (String.areAnyNotEmpty(authToken, cookie)) {
                addInterceptor(RequestHeaderInterceptor(authToken, cookie))
            }
            cookieResponseListener?.let {
                addInterceptor(ResponseCookiesInterceptor(it))
            }
            networkInterceptor?.let {
                networkInterceptors().add(networkInterceptor)
            }
            readTimeout(timeOutDurationSeconds, TimeUnit.SECONDS)
            connectTimeout(timeOutDurationSeconds, TimeUnit.SECONDS)
            if (acceptAllCerts) {
                sslSocketFactory(
                    getAllAcceptingSslFactory(),
                    getAllTrustingCertManager()[0] as X509TrustManager
                )
                hostnameVerifier(getAllAcceptingHostNameVerifier())
            }
        }.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
        return retrofit.create(serviceClass)
    }

    private fun getAllAcceptingSslFactory(): SSLSocketFactory {
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, getAllTrustingCertManager(), java.security.SecureRandom())
        return sslContext.socketFactory
    }

    @SuppressLint("TrustAllX509TrustManager")
    private fun getAllTrustingCertManager(): Array<TrustManager> =
        arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }
        })

    @SuppressLint("BadHostnameVerifier")
    private fun getAllAcceptingHostNameVerifier(): HostnameVerifier = object : HostnameVerifier {
        override fun verify(hostname: String?, session: SSLSession?): Boolean {
            return true
        }
    }
}