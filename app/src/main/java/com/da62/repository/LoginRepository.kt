package com.da62.repository

import com.da62.datasource.api.ApiService
import com.da62.datasource.local.LoginLocalDataSource
import com.da62.model.User
import com.da62.model.UserData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface LoginRepository {

    fun postLogin(kakaoToken: String): Single<UserData>

    fun saveUser(user: User)
}

class LoginRepositoryImpl(
    private val apiService: ApiService,
    private val localDataSource: LoginLocalDataSource
) : LoginRepository {

    override fun postLogin(kakaoToken: String): Single<UserData> =
            apiService.postLogin(kakaoToken)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    override fun saveUser(user: User) = localDataSource.saveUser(user)
}