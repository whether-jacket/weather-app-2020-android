package com.seljabali.templateapplication.network

import com.seljabali.templateapplication.models.GithubProfile
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

interface GithubService {

    @GET("users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<GithubProfile>

//    @GET
//    fun getFile(@Url devicesSettingsUrl: String = "https://someUrl/com/file.json"): Observable<ResponseBody>

//    @POST("/changePassword")
//    fun login(@Body loginRequest: ChangePasswordRequest): Observable<ResponseBody>
}