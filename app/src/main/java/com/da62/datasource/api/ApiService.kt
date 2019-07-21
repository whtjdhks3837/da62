package com.da62.datasource.api

import com.da62.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @POST("/api/login")
    fun postLogin(@Body kakaoToken: String): Single<User>

    @POST("/api/plants/save")
    fun savePlant(
        @Header("Authorization") token: String,
        @Header("Content-Type") contentType: String = "application/json",
        @Body plantDto: SavePlant,
        @Query("userId") userId: Int
    ): Single<String>

    @POST("/api/getPlantNames")
    fun getPlantNames(
        @Header("Authorization") token: String,
        @Query("word") word: String
    ): Observable<List<String>>
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