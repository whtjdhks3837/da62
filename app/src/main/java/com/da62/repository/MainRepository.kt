package com.da62.repository

import com.da62.model.Plant

interface MainRepository {

    fun getPlantList(): List<Plant>
}

class MainRepositoryImpl : MainRepository {

    override fun getPlantList(): List<Plant> {
        val plantList = mutableListOf<Plant>()

        for (i in 0 until 5) {
            plantList.add(Plant(i, "다육2", "다육2는 기여어"))
        }

        return plantList
    }
}