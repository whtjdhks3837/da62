package com.da62.presenter.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import org.jetbrains.anko.AnkoLogger

open class BaseViewModel : ViewModel(), AnkoLogger {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}