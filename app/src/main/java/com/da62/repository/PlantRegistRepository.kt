package com.da62.repository

import com.da62.datasource.local.PlantSpeciesLocalDataSource
import io.reactivex.Single

interface PlantRegistRepository {

    fun getSpeciesTextList(input: String): Single<List<String>>
}

class PlantRegistRepositoryImpl(private val dataSource: PlantSpeciesLocalDataSource) : PlantRegistRepository {

    override fun getSpeciesTextList(input: String) =
        Single.create<List<String>> { emitter ->
            emitter.onSuccess(dataSource.getPlantSpecies(input))
        }
}