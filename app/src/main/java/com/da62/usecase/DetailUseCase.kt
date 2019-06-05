package com.da62.usecase

import com.da62.repository.DetailRepository

interface DetailUseCase

class DetailUseCaseImpl(private val repository: DetailRepository) : DetailUseCase