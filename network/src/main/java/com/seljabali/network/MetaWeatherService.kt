package com.seljabali.network

import com.seljabali.network.responses.Location
import com.seljabali.network.responses.WeatherForLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {
    //    @GET
    //    fun getFile(@Url devicesSettingsUrl: String = "https://someUrl/com/file.json"): Observable<ResponseBody>

    //    @POST("/changePassword")
    //    fun login(@Body loginRequest: ChangePasswordRequest): Observable<ResponseBody>

    @GET("/api/location/{whereOnEarthId}")
    fun getWeatherForWhereOnEarthId(@Path("whereOnEarthId") whereOnEarthId: Int): Observable<WeatherForLocation>

    @GET("/api/location/search")
    fun getLocationsForCitySearch(@Query("query") cityName: String): Observable<List<Location>>
}
