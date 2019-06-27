package com.da62.presenter.write.plant

import android.view.View
import androidx.lifecycle.LiveData
import com.da62.presenter.base.BaseViewModel
import com.da62.util.SingleLiveEvent

class PlantRegistViewModel : BaseViewModel() {

    private val _next = SingleLiveEvent<Any>()

    val next: LiveData<Any> = _next

    fun onNextClick(view: View) {
        _next.call()
    }
}