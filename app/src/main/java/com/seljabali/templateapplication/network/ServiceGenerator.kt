package com.seljabali.templateapplication.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seljabali.core.network.interceptors.RequestHeaderInterceptor
import com.seljabali.core.network.interceptors.ResponseCookiesInterceptor
import com.seljabali.core.utilities.App
import com.seljabali.core.utilities.threeten.ZonedDateTimeTypeAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator<S>(
    private val apiBaseUrl: String,
    private val serviceClass: Class<S>,
    private val authToken: String = "",
    private var cookie: String = "",
    private val timeOutTimeSeconds: Long = DEFAULT_TIME_OUT_SECONDS) : ResponseCookiesInterceptor.CookieListener {

    companion object {
        const val DEFAULT_TIME_OUT_SECONDS: Long = 20L
        val gson: Gson by lazy { GsonBuilder()
//            .registerTypeHierarchyAdapter(ZonedDateTime::class.java.javaClass, ZonedDateTimeTypeAdapterFactory())
            .registerTypeAdapter(ZonedDateTime::class.java.javaClass, ZonedDateTimeTypeAdapterFactory())
            .create()
        }
    }

    private var service: S? = null

    init {
        initialize()
    }

    // TODO: Remove cookie off class
    override fun saveCookies(hashSet: HashSet<String>) {
        for (cookie in hashSet.iterator()) {
            this.cookie = cookie
        }
        initialize()
    }

    fun get(): S = service!!

    fun clearCookies() {
        this.cookie = ""
        initialize()
    }

    fun getCookie(): String = cookie

    fun setCookie(cookie: String) {
        this.cookie = cookie
        initialize()
    }

    private fun initialize() {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (App.isInDebugMode()) BODY else NONE
        }
        val httpClient: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            addInterceptor(
                RequestHeaderInterceptor(
                    authToken,
                    cookie
                )
            )
            addInterceptor(ResponseCookiesInterceptor(this@ServiceGenerator))
            if (App.isInDebugMode()) {
                networkInterceptors().add(StethoInterceptor())
            }
            readTimeout(timeOutTimeSeconds, TimeUnit.SECONDS)
            connectTimeout(timeOutTimeSeconds, TimeUnit.SECONDS)
        }.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
        service = retrofit.create(serviceClass)
    }
}