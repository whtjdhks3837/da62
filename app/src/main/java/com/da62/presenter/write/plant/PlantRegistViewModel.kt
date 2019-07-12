package com.da62.presenter.write.plant

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.PlantRegistUseCase
import com.da62.util.SingleLiveEvent
import com.da62.util.add

class PlantRegistViewModel(private val useCase: PlantRegistUseCase) : BaseViewModel() {

    private val _error = SingleLiveEvent<String>()

    val error: LiveData<String> = _error

    private val _next = SingleLiveEvent<Any>()

    val next: LiveData<Any> = _next

    private val _speciesImage = MutableLiveData<View?>()

    val speciesImage: LiveData<View?> = _speciesImage

    val species = SingleLiveEvent<String>()

    private val _searchResult = SingleLiveEvent<List<String>>()

    val searchResult: LiveData<List<String>> = _searchResult

    val nickName = SingleLiveEvent<String>()

    private val _plantLevel = SingleLiveEvent<Int>()

    val plantLevel: LiveData<Int> = _plantLevel

    val waterDate = SingleLiveEvent<String>().apply {
        value = ""
    }

    val waterOften = SingleLiveEvent<Int>().apply {
        value = 0
    }

    val waterTime = SingleLiveEvent<String>().apply {
        value = ""
    }

    val isWaterAlarmChecked = SingleLiveEvent<Boolean>().apply {
        value = false
    }

    fun inputSpeciesText(input: String) {
        compositeDisposable add useCase.getSpeciesTextList(input)
            .subscribe({
                _searchResult.value = it
            }, {
                it.printStackTrace()
            })
    }

    fun onNextClick(view: View) {
        _next.call()
    }

    fun onSpeciesImageChecked(button: CompoundButton, isChecked: Boolean) {
        if (!isChecked) {
            if (_speciesImage.value?.id == button.id) {
                _speciesImage.value = null
            }
        } else {
            _speciesImage.value = button
        }
    }

    fun onSpeciesFragmentNextClick(view: View) {
        if (!speciesInputValidate()) {
            _error.value = "빈 칸 없이 입력해주세요."
            return
        }
        if (!speciesImageValidate()) {
            _error.value = "일러스트를 선택해주세요."
            return
        }
        _next.call()
    }

    private fun speciesInputValidate() =
        species.value?.isNotEmpty() ?: false

    private fun speciesImageValidate() =
        speciesImage.value?.let { true } ?: false

    fun onInfoFragmentNextClick(view: View) {
        if (!nickNameInputValidate()) {
            _error.value = "빈 칸 없이 입력해주세요."
            return
        }
        if (!levelValidate()) {
            _error.value = "단계를 선택해주세요."
            return
        }
        _next.call()
    }

    private fun nickNameInputValidate() =
        nickName.value?.isNotEmpty() ?: false

    private fun levelValidate() =
        _plantLevel.value?.let { true } ?: false

    fun setPlantLevel(id: Int) {
        _plantLevel.value = id
    }

    fun onWaterFragmentCompleteClick(view: View) {
        if (waterDateValidate()) {
            _error.value = "날짜를 입력해주세요."
            return
        }
        if (waterOftenValidate()) {
            _error.value = "주기를 입력해주세요."
            return
        }
        if (waterTimeValidate()) {
            _error.value = "시간을 입력해주세요."
            return
        }
    }

    private fun waterDateValidate() =
        waterDate.value?.isNotEmpty() ?: throw IllegalStateException()

    private fun waterOftenValidate() =
        waterOften.value?.let { it > 0 } ?: throw IllegalStateException()

    private fun waterTimeValidate() =
        waterTime.value?.isNotEmpty() ?: throw IllegalStateException()
}