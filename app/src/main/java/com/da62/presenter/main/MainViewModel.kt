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

    // plant id
    private val _clickToItem = MutableLiveData<Int>()
    val clickToItem: LiveData<Int>
        get() = _clickToItem

    init {
        _plantList.value = useCase.getPlantList()
    }

    override fun onItemClick(position: Int, plant: Plant) {
        _clickToItem.value = plant.id
    }
}

interface MainEventListener {

    fun onItemClick(position: Int, plant: Plant)
}