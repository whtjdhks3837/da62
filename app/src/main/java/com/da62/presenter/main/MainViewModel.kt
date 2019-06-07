package com.da62.presenter.main

import com.da62.presenter.base.BaseViewModel
import com.da62.usecase.MainUseCase

class MainViewModel(
    private val useCase: MainUseCase
) : BaseViewModel(), MainEventListener {

}

interface MainEventListener {

}