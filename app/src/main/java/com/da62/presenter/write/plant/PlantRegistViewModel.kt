package com.da62.presenter.write.plant

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import com.da62.presenter.base.BaseViewModel
import com.da62.util.SingleLiveEvent

class PlantRegistViewModel : BaseViewModel() {

    private val _mNext = SingleLiveEvent<Any>()

    val mNext: LiveData<Any> = _mNext

    fun onNextClick(view: View) {
        _mNext.call()
    }
}