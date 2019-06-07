package com.da62.presenter.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.da62.model.ListType
import com.da62.model.Plant
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.MainUseCase

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel(), MainEventListener {

    private var viewType = ListType.LIST

    private val _plantList = MutableLiveData<List<Plant>>()
    val plantList: LiveData<List<Plant>>
        get() = _plantList

    // plant id
    private val _clickToItem = MutableLiveData<Int>()
    val clickToItem: LiveData<Int>
        get() = _clickToItem

    private val _clickToViewType = MutableLiveData<ListType>()
    val clickToViewType: LiveData<ListType>
        get() = _clickToViewType

    init {
        _plantList.value = useCase.getPlantList()
    }

    override fun onItemClick(position: Int, plant: Plant) {
        _clickToItem.value = plant.id
    }

    fun clickToViewType() {
        _clickToViewType.value = changeViewType()
    }

    private fun changeViewType(): ListType {
        viewType = when (viewType) {
            ListType.LIST -> ListType.GRID
            else -> ListType.LIST
        }
        return viewType
    }
}

interface MainEventListener {

    fun onItemClick(position: Int, plant: Plant)
}