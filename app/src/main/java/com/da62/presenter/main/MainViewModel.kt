package com.da62.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.da62.model.Plant
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.MainUseCase

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel(), MainEventListener {

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList: LiveData<List<Plant>>
        get() = _plantList

    init {
        _plantList.value = useCase.getPlantList()
    }
}

interface MainEventListener {

}