package com.seljabali.network

import com.seljabali.network.responses.WeatherForLocation
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface MetaWeatherService {
    //    @GET("users/{username}")
    //    fun getGithubUser(@Path("username") username: String): Observable<GithubProfile>

    //    @GET
    //    fun getFile(@Url devicesSettingsUrl: String = "https://someUrl/com/file.json"): Observable<ResponseBody>

    //    @POST("/changePassword")
    //    fun login(@Body loginRequest: ChangePasswordRequest): Observable<ResponseBody>

    @GET("/api/location/{locationId}")
    fun getWeatherForLocation(@Path("locationId") locationId: Int): Observable<WeatherForLocation>


}
