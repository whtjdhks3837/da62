package com.da62.presenter.splash

import androidx.lifecycle.LiveData
import com.da62.presenter.base.BaseViewModel
import com.da62.util.SingleLiveEvent
import com.da62.util.add
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class SplashViewModel : BaseViewModel() {

    private val _openToMain = SingleLiveEvent<Any>()
    val openToMain: LiveData<Any>
        get() = _openToMain

    init {
        compositeDisposable add Completable.timer(3, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _openToMain.call() }, {})
    }
}