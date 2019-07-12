package com.da62.datasource.di

import com.da62.datasource.api.apiModules
import com.da62.datasource.local.localDataSourceModules
import com.da62.presenter.viewModelModules
import com.da62.repository.repositoryModules
import com.da62.usecase.useCaseModules


val appModules = listOf(apiModules, viewModelModules, repositoryModules, useCaseModules, localDataSourceModules)