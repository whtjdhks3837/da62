package com.da62.presenter.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.da62.model.Plant
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.DetailUseCase
import com.da62.util.SingleLiveEvent
import com.da62.util.add
import io.reactivex.android.schedulers.AndroidSchedulers
import org.jetbrains.anko.error

class DetailViewModel(private val useCase: DetailUseCase) : BaseViewModel() {

    private var plantId: Int = 0

    private val _plantInfoList = MutableLiveData<List<Plant>>()
    val plantInfoList: LiveData<List<Plant>>
        get() = _plantInfoList

    private val _clickToBack = SingleLiveEvent<Any>()
    val clickToBack: LiveData<Any>
        get() = _clickToBack

    private val _plant = MutableLiveData<Plant>()
    val plant: LiveData<Plant>
        get() = _plant

    private val _name = MediatorLiveData<String>()
    val name: LiveData<String>
        get() = _name

    private val _loveGauge = MediatorLiveData<Int>()
    val loveGauge: LiveData<Int>
        get() = _loveGauge

    private val _waterGauge = MediatorLiveData<Int>()
    val waterGauge: LiveData<Int>
        get() = _waterGauge

    private val _clickToGallery = SingleLiveEvent<Int>()
    val clickToGallery: LiveData<Int>
        get() = _clickToGallery

    private val _card = MutableLiveData<String>()
    val card: LiveData<String>
        get() = _card

    init {

        _name.addSource(plant) {
            _name.value = it.name
        }

        _loveGauge.addSource(plant) {
            _loveGauge.value = it.love
        }
        _waterGauge.addSource(plant) {
            _waterGauge.value = it.waterDate
        }

        _plantInfoList.value = useCase.getInfoList()
    }

    fun clickToBack() {
        _clickToBack.call()
    }

    fun loadDetail(id: Int) {
        compositeDisposable add useCase.getDetail(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _plant.value = it
                plantId = id
            }, {

            })
    }

    fun clickToGallery() {
        _clickToGallery.value = plantId
    }

    fun configureThumbnail(card: String) {
        _card.value = card
    }
}