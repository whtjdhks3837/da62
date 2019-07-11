package com.da62.usecase

import com.da62.repository.PlantRegistRepository
import io.reactivex.Single

interface PlantRegistUseCase {

    fun getSpeciesTextList(input: String): Single<List<String>>
}

class PlantRegistUseCaseImpl(private val repository: PlantRegistRepository) : PlantRegistUseCase {

    override fun getSpeciesTextList(input: String) =
        repository.getSpeciesTextList(input)
}