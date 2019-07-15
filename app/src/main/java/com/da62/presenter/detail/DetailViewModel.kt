package com.da62.presenter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.da62.model.Plant
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.DetailUseCase
import com.da62.util.SingleLiveEvent

class DetailViewModel(private val useCase: DetailUseCase) : BaseViewModel() {

    private val _plantInfoList = MutableLiveData<List<Plant>>()
    val plantInfoList: LiveData<List<Plant>>
        get() = _plantInfoList

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    init {

        _plantInfoList.value = useCase.getInfoList()
    }

    fun clickToBack() {
        _clickToBack.call()
    }
}