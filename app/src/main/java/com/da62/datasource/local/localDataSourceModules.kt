package com.da62.datasource.local

import org.koin.dsl.module

val localDataSourceModules = module {

    factory {
        PlantSpeciesLocalDataSourceImpl() as PlantSpeciesLocalDataSource
    }
}