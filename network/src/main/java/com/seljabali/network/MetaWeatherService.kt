package com.seljabali.network

import com.seljabali.network.responses.WeatherForLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MetaWeatherService {

    @GET("/api/location/{locationId}")
    fun getWeatherForLocation(@Path("locationId") locationId: Int): Observable<WeatherForLocation>

}
