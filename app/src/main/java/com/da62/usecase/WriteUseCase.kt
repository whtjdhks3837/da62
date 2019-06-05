package com.da62.usecase

import com.da62.repository.WriteRepository

interface WriteUseCase

class WriteUseCaseImpl(private val repository: WriteRepository) : WriteUseCase