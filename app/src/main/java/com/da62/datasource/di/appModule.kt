package com.da62.datasource.di

import com.da62.presenter.viewModelModules
import com.da62.repository.repositoryModules
import com.da62.usecase.useCaseModules


val appModules = listOf(viewModelModules, repositoryModules, useCaseModules)