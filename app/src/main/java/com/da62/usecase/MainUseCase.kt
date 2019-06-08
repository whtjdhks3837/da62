package com.da62.usecase

import com.da62.model.Plant
import com.da62.repository.MainRepository

interface MainUseCase {

    fun getPlantList(): List<Plant>
}

class MainUseCaseImpl(private val repository: MainRepository) : MainUseCase {

    override fun getPlantList(): List<Plant> = repository.getPlantList()
}