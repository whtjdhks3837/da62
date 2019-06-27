package com.da62.repository

import com.da62.datasource.api.ApiService
import com.da62.datasource.api.KakaoApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface LoginRepository {

    fun postKakaoProfile(accessToken: String): Single<Int>

    fun postKakaoId(id: Int): Single<Any>
}

class LoginRepositoryImpl(
    private val apiService: ApiService,
    private val kakaoApiService: KakaoApiService) : LoginRepository {

    override fun postKakaoProfile(accessToken: String): Single<Int> =
        kakaoApiService.postKakaoProfile("Bearer $accessToken")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.id
            }

    override fun postKakaoId(id: Int): Single<Any> =
        apiService.postKakaoId()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}