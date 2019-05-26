package com.da62.di

import com.da62.ui.MainViewModel
import com.da62.ui.login.LoginViewModel
import com.da62.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {
    viewModel { SplashViewModel() }
}

val mainModule = module { viewModel { MainViewModel() } }

val loginModule = module { viewModel { LoginViewModel() } }

val appModules = listOf(splashModule, mainModule, loginModule)