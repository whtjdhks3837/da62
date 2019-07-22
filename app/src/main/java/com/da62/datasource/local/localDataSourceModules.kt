package com.da62.datasource.local

import org.koin.dsl.module

val localDataSourceModules = module {

    factory {
        LoginLocalDataSourceImpl() as LoginLocalDataSource
    }
}