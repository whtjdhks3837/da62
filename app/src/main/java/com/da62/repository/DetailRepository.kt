package com.da62.repository

import com.da62.datasource.api.ApiService
import com.da62.datasource.local.PreferenceStorage
import com.da62.model.Plant
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DetailRepository {

    fun getDetail(id: Int): Single<Plant>
}

class DetailRepositoryImpl(
    private val apiService: ApiService,
    private val preferenceStorage: PreferenceStorage
) : DetailRepository {

    override fun getDetail(id: Int): Single<Plant> {
        /*return apiService.getDetail(
            accessToken = preferenceStorage.accessToken ?: "",
            id = id
        ).subscribeOn(Schedulers.io())
            .map { dummy }*/

        return Single.just(dummy)
    }

    private val dummy = Plant(
        id = 2,
        name = "다육이",
        description = "test",
        alarm = false,
        card = "FLOWER",
        kind = "123",
        waterDate = 4,
        love = 7
    )
}