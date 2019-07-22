package com.da62.usecase

import com.da62.model.User
import com.da62.repository.LoginRepository
import io.reactivex.Single

interface LoginUseCase {

    fun login(kakaoToken: String): Single<User>

    fun saveUser(user: User)
}

class LoginUseCaseImpl(private val repository: LoginRepository) : LoginUseCase {

    override fun login(kakaoToken: String) = repository.postLogin(kakaoToken)

    override fun saveUser(user: User) = repository.saveUser(user)
}