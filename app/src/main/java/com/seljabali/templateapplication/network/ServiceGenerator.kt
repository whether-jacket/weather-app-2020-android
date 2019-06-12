package com.seljabali.templateapplication.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.seljabali.templateapplication.utilities.App
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator<S>(
    apiBaseUrl: String,
    serviceClass: Class<S>,
    private val authToken: String = "",
    private val timeOutTimeSeconds: Long = DEFAULT_TIME_OUT_SECONDS) {

    companion object {
        const val AUTH_HEADER = "Authorization"
        const val DEFAULT_TIME_OUT_SECONDS: Long = 60L
    }

    private val service: S

    init {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (App.isInDebugMode()) BODY else NONE
        }
        val httpClient: OkHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
            readTimeout(timeOutTimeSeconds, TimeUnit.SECONDS)
            connectTimeout(timeOutTimeSeconds, TimeUnit.SECONDS)
            if (authToken.isNotEmpty()) {
                addInterceptor(AuthHeaderInterceptor(authToken))
            }
        }.build()
        val gson: Gson = GsonBuilder()
//            .registerTypeAdapter(Foo::class, FooDeserializer()).create()
            .create()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
        service = retrofit.create(serviceClass)
    }

    fun build(): S = service

    class AuthHeaderInterceptor(private val authToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response = chain.run {
            proceed(
                request()
                    .newBuilder()
                    .header(AUTH_HEADER, authToken)
                    .build()
            )
        }
    }
}