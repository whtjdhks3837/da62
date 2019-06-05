package com.da62.usecase

import com.da62.repository.MainRepository

interface MainUseCase

class MainUseCaseImpl(private val repository: MainRepository) : MainUseCase