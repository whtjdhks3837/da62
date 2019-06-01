package com.da62.usecase

import com.da62.repository.LoginRepository

interface LoginUseCase

class LoginUseCaseImpl(private val repository: LoginRepository) : LoginUseCase