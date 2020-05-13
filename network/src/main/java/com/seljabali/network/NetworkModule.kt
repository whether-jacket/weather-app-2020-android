package com.seljabali.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.seljabali.core.BuildConfig
import com.seljabali.network.interceptors.RequestHeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CALL_TIMEOUT = 5L
private const val BASE_URL = "https://www.metaweather.com"

val networkModule = module {

    single<MetaWeatherService> {
        get<Retrofit>().create(MetaWeatherService::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(get<GsonConverterFactory>())
            .addCallAdapterFactory(get<RxJava2CallAdapterFactory>())
            .build()
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            callTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            // readTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            // connectTimeout(CALL_TIMEOUT, TimeUnit.SECONDS)
            addNetworkInterceptor(get<RequestHeaderInterceptor>())
            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(get<StethoInterceptor>())
                addInterceptor(get<HttpLoggingInterceptor>())
            }
        }.build()
    }

    single<StethoInterceptor> {
        StethoInterceptor()
    }

    single<RequestHeaderInterceptor> {
        RequestHeaderInterceptor()
    }

    single<GsonConverterFactory> {
        GsonConverterFactory.create()
    }

    single<Gson> {
        Gson()
    }

    single<RxJava2CallAdapterFactory> {
        RxJava2CallAdapterFactory.create()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    }
}
