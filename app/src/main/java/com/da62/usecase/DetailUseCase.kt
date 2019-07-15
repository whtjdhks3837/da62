package com.da62.usecase

import com.da62.model.Plant
import com.da62.repository.DetailRepository

interface DetailUseCase {

    fun getInfoList(): List<Plant>
}

class DetailUseCaseImpl(private val repository: DetailRepository) : DetailUseCase {

    override fun getInfoList(): List<Plant> {
        val dummy = mutableListOf<Plant>()

        for (i in 0 until 4) {
            dummy.add(Plant())
        }

        return dummy
    }

}