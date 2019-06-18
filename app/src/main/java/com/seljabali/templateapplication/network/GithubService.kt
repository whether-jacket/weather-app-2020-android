package com.seljabali.templateapplication.network

import com.seljabali.templateapplication.models.GithubProfile
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    fun getGithubUser(@Path("username") username: String): Observable<GithubProfile>
}