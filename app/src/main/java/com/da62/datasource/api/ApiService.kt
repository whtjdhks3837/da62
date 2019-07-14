package com.da62.datasource.api

import com.da62.model.KakaoProfile
import com.da62.model.KakaoTalkProfile
import com.da62.model.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    fun postLogin(@Body kakaoToken: String): Single<User>
}

interface KakaoApiService {

    @GET("/v1/api/talk/profile")
    fun getKakaoTalkProfile(
        @Header("Authorization") accessToken: String
    ): Single<KakaoTalkProfile>

    @POST("/v2/user/me")
    fun postKakaoProfile(
        @Header("Authorization") accessToken: String
    ): Single<KakaoProfile>
}