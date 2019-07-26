package com.da62.presenter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.da62.model.Plant
import com.da62.model.PlantInfo
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.DetailUseCase
import com.da62.util.SingleLiveEvent
import com.da62.util.add
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*

class DetailViewModel(private val useCase: DetailUseCase) : BaseViewModel() {

    private var plantId: Int = 0

    private val _plantInfo = MutableLiveData<PlantInfo>()
    val plantInfo: LiveData<PlantInfo>
        get() = _plantInfo

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    private val _plant = MutableLiveData<Plant>()
    val plant: LiveData<Plant>
        get() = _plant

    private val _clickToGallery = SingleLiveEvent<Int>()
    val clickToGallery: LiveData<Int>
        get() = _clickToGallery

    private val _card = MutableLiveData<String>()
    val card: LiveData<String>
        get() = _card

    private val _visibleProgress = MutableLiveData<Boolean>()
    val visibleProgress: LiveData<Boolean>
        get() = _visibleProgress

    val raiseDate: LiveData<String> = Transformations.map(plant) {
        val diff = it.raiseDate.time - Date().time
        val diffDays = diff / (24 * 60 * 60 * 1000)
        "${diffDays}일후"
    }

    fun clickToBack() {
        _clickToBack.call()
    }

    fun loadDetail(id: Int) {
        compositeDisposable add useCase.getDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _visibleProgress.postValue(true) }
            .subscribe({
                plantId = id
                updateUI(it)
                _visibleProgress.value = false
            }, {
                _visibleProgress.postValue(false)
                it.printStackTrace()
            })
    }

    fun clickToGallery() {
        _clickToGallery.value = plantId
    }

    private fun updateUI(plant: Plant) {
        _plant.value = plant
        _plantInfo.value = plant.plantInfo
    }

    fun configureThumbnail(card: String) {
        _card.value = card
    }
}