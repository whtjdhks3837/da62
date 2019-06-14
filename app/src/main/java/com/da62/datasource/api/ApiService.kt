package com.da62.datasource.api

import com.da62.model.KakaoProfile
import com.da62.model.KakaoTalkProfile
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("post the id to server")
    fun postKakaoId(): Single<Any>
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