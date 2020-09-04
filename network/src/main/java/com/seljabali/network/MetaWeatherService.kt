package com.seljabali.network

import com.seljabali.network.responses.WeatherForLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MetaWeatherService {
    //    @GET
    //    fun getFile(@Url devicesSettingsUrl: String = "https://someUrl/com/file.json"): Observable<ResponseBody>

    //    @POST("/changePassword")
    //    fun login(@Body loginRequest: ChangePasswordRequest): Observable<ResponseBody>

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Observable<WeatherForLocation>

    @GET("/api/location/search/?query={cityName}")
    fun getWeatherForCitySearch(@Path("cityName") cityName: String): Observable<WeatherForLocation>
}
