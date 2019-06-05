package com.da62.repository

import org.koin.dsl.module

val repositoryModules = module {
    factory { LoginRepositoryImpl() as LoginRepository }

    factory { WriteRepositoryImpl() as WriteRepository }
}